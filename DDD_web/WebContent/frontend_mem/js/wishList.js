function load() {
    $(document).scroll(function(e) {
        var scrollTop = $(document).scrollTop();
        if (scrollTop > 60) {
            $('#top_header1').slideDown();
        } else {
            $('#top_header1').slideUp();

        }
    });
}
window.onload = load;
