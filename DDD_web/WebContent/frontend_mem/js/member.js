function load() {

    (function() {
        // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
        if (!String.prototype.trim) {
            (function() {
                // Make sure we trim BOM and NBSP
                var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                String.prototype.trim = function() {
                    return this.replace(rtrim, '');
                };
            })();
        }

        [].slice.call(document.querySelectorAll('input.input__field')).forEach(function(inputEl) {
            // in case the input is already filled..
            if (inputEl.value.trim() !== '') {
                classie.add(inputEl.parentNode, 'input--filled');
            }

            // events:
            inputEl.addEventListener('focus', onInputFocus);
            inputEl.addEventListener('blur', onInputBlur);
        });

        function onInputFocus(ev) {
            classie.add(ev.target.parentNode, 'input--filled');
        }

        function onInputBlur(ev) {
            if (ev.target.value.trim() === '') {
                classie.remove(ev.target.parentNode, 'input--filled');
            }
        }
    })();

    $(document).scroll(function(e) {
        var scrollTop = $(document).scrollTop();
        if (scrollTop > 60) {
            $('#top_header1').slideDown();
        } else {
            $('#top_header1').slideUp();

        }
    });
    
    if (window.FileReader) { //測試瀏覽器
        document.getElementById("myFile").onchange = function() {

            for (var i = 0, file; file = this.files[i]; i++) { //var file;
                var reader = new FileReader();
                reader.onloadend = (function(file) {
                    return function() {
                        document.getElementById('image_output').innerHTML += '<img src="' + this.result + '" height="50"/> <br/>';
                    }
                })(file); //自己CALL自己
                reader.readAsDataURL(file);
            }
        }
    } else {
        alert("瀏覽器不支援 HTML 5");
    }

}
window.onload = load;
