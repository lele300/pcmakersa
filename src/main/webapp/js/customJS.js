//Modal
$(document).ready(function () {

    $('.modal').modal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        inDuration: 300, // Transition in duration
        outDuration: 200, // Transition out duration
        startingTop: '3%', // Starting top style attribute
        endingTop: '11%' // Ending top style attribute   
    });
});

//Dropdown 

$('.dropdown-button').dropdown({
    inDuration: 300,
    outDuration: 225,
    constrainWidth: true, // Does not change width of dropdown to that of the activator
    hover: true, // Activate on hover
    gutter: 0, // Spacing from edge
    belowOrigin: true, // Displays dropdown below the button
    alignment: 'left', // Displays dropdown with edge aligned to the left of button
    stopPropagation: false// Stops event propagation
}
);

$("#header-plugin").load("http://vivinantony.github.io/header-plugin/", function () {
    $("a.back-to-link").attr("href", "http://blog.thelittletechie.com/2015/05/different-social-nav-styles.html#tlt");
});

$(document).ready(function () {
    $('select').material_select();
});









