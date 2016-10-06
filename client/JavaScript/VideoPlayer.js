/**
 * Created by Loukik on 01-Oct-16.
 */


function play(url) {

    console.log("Playing video");
    var modal = document.getElementById('vidModal');
    modal.modal();
    var video = document.getElementById('video');
    var source = document.createElement('source');

    source.setAttribute('src', url);

    video.appendChild(source);
    video.play();
}