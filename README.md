![header](https://github.com/user-attachments/assets/59fb4f0e-498d-4508-9457-1ed4a7e211d9)

### The Brief

BritBeat Record shop has a [robust new API](https://github.com/QWang00/record-shop). It is a modern British record shop with two branches of the Android app. One branch, **iTunes-api-full-feature**, uses the iTunes API to retrieve album images based on the artist and album name. It includes full features for adding, updating, deleting, and filtering albums. Another branch, **s3-image-upload** connects with a custom Spring Boot backend, where album images are uploaded to AWS S3, and the image URL is returned.

### Learnings
Android Studio

RecyclerView

Two-way data binding

Custom activities with click handling

.xml layout creation

MVVM architecture

Retrofit

Interacting with an external API

Spinner as a dropdown menu

Data filtering

Cloud and Third-Party API Integration (AWS S3, iTunes API)

### Screenshots
#### Main display with RecyclerView

<img src="https://github.com/user-attachments/assets/831ffe73-2671-45e5-9b99-0c08e5b54684" alt="Display with Filter" width="350"/>

#### Add a new album with an iTunes image. 
If there's a typo in the album name, a placeholder image appears. 

Correcting the typo displays the correct image for Park Life.

<img src="https://github.com/user-attachments/assets/3be04676-52ca-4f6a-ae3d-b73cf11b15e3" alt="Add with iTunes Year Verification" width="350"/>

#### Add new album with uploading images to S3
Even with a typo, the album will still have an image, as it's selected from the user's device.

<img src="https://github.com/user-attachments/assets/f1e368d8-5ec2-41be-b92a-d7e8f7f20307" alt="Add in S3" width="350"/>

#### Filter
<img src="https://github.com/user-attachments/assets/1c2e7547-cfae-401a-8898-4f8e9e7bd4fb" alt="Filtering GIF" width="350"/>

#### Update & delete an album
<img src="https://github.com/user-attachments/assets/abf66d1f-296d-4c84-8abb-cf58c876f035" alt="Update iTunes" width="350"/>




