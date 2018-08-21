function onSuccess(result) {
    $('.card-img-top').src = result.url;
    $('#btnDeleteImage').classList.remove('invisible');
}

function imageUploadHandler() {
    //TODO formData 사용하는 이유?
    var formData = new FormData();
    formData.append('data', $("#btnImageUpload").files[0]);

    fetchManager({
        url: '/upload',
        method: 'POST',
        body: formData,
        callback: onSuccess
    });
}

function imageDeleteHandler() {
    $('.card-img-top').src = '';
    $('#btnDeleteImage').classList.add('invisible');
    $("#btnImageUpload").value = '';
}

function imageSubmitHandler() {
    var formData = new FormData();
    formData.append('contents', document.querySelector('.card-text').value);
    if(document.getElementById("btnImageUpload").files.length != 0) {
        formData.append('image', $("#btnImageUpload").files[0]);
    }

    fetchManager({
        url: '/create',
        method: 'POST',
        body: formData,
        callback: onSuccess
    });
}

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector('#btnImageUpload').addEventListener("change", imageUploadHandler);
    document.querySelector('#btnDeleteImage').addEventListener("click", imageDeleteHandler);
    document.querySelector('#btnSubmit').addEventListener("click", imageSubmitHandler);
});

import {$, fetchManager} from "../utils/utils.js";