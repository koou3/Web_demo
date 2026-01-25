/**
 * 画面初期化
 */
function transitionTo(linkIndex) {
	$("#transitionTo").val(linkIndex);
	$("#transitionTo").trigger("click");
}

function linktest(linkIndex) {
	alert("1");
	$("#transitionTo").val(linkIndex);
	alert("2");
	$("#transitionTo").trigger("click");
	alert("3");
}
