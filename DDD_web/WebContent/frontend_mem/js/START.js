function load() {
    $(".animsition").animsition({

        inClass: '',
        outClass: 'fade-out-up-lg',
        inDuration: 1500,
        outDuration: 2000,
        linkElement: '.animsition-link',
        // e.g. linkElement   :   'a:not([target="_blank"]):not([href^=#])'
        loading: false,
        loadingParentElement: 'body', //animsition wrapper element
        loadingClass: 'animsition-loading',
        unSupportCss: ['animation-duration',
            '-webkit-animation-duration',
            '-o-animation-duration'
        ],
        //"unSupportCss" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
        //The default setting is to disable the "animsition" in a browser that does not support "animation-duration".

        overlay: false,

        overlayClass: 'animsition-overlay-slide',
        overlayParentElement: 'body'
    });


    $('.img').mouseover(function() {
        $('.img').css("width", "70");
    });

    $('.img').mouseout(function() {
        $('.img').css("width", "50");
    });

}
window.onload = load;
