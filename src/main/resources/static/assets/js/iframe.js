/**
 * Created by liu on 15-12-23.
 */
function iframeAuto(){
    //document.domain = "http://127.0.0.1:63342";//放到线上要指向指向根域
    $(window.parent.document).find("#iframeMain").load(function(){
        var main = $(window.parent.document).find("#iframeMain");
        var thisheight = $(document).height();
        main.height(thisheight < 500 ? 500 : thisheight);
    });
}





