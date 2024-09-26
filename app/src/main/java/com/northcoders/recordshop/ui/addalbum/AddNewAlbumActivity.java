package com.northcoders.recordshop.ui.addalbum;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.service.AlbumApiService;
import com.northcoders.recordshop.service.RetrofitInstance;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandler handler;
    private Album album;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        album = new Album();
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_add_new_album
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handler = new AddAlbumClickHandler(album, this, viewModel);

        binding.setAlbum(album);
        binding.setClickHandler(handler);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImage = result.getData().getData();
                        ImageView albumImageView = binding.imagePreview;
                        albumImageView.setImageURI(selectedImage);
                        String fileName = getFileName(selectedImage);
                        binding.fileNameTextView.setText(fileName != null ? fileName : "File Name Unavailable");

                        binding.uploadImageButton.setText("Image Selected");
                        uploadImage(selectedImage);
                    }
                });

        handler.setImagePickerLauncher(imagePickerLauncher);
    }


    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex >= 0) {
                        result = cursor.getString(nameIndex);
                    } else {
                        result = "File Name Unavailable";
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void uploadImage(Uri selectedImageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] bytes = getBytes(inputStream);

            RequestBody requestFile = RequestBody.create(
                    MediaType.parse(getContentResolver().getType(selectedImageUri)), bytes
            );

            MultipartBody.Part body = MultipartBody.Part.createFormData(
                    "file", getFileName(selectedImageUri), requestFile
            );

            AlbumApiService service = RetrofitInstance.getService();
            Call<ResponseBody> call = service.uploadImage(body);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String imageUrl = response.body().string();

                            album.setImageUrl(imageUrl);

                            Toast.makeText(AddNewAlbumActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(AddNewAlbumActivity.this, "Failed to read response", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            String errorBody = response.errorBody().string();
                            Toast.makeText(AddNewAlbumActivity.this, "Image upload failed: " + errorBody, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(AddNewAlbumActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(AddNewAlbumActivity.this, "Image upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to read image data", Toast.LENGTH_SHORT).show();
        }
    }

}