if (statue.checked) {
    $(".cbox8 input").removeAttr("disabled");
} else {
    $(".cbox8 input").attr("disabled", "disabled");
    $(".cbox8 input").attr("checked", false);
}
