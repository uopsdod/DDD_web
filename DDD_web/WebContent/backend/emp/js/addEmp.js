function load() {
	document.getElementById("magic").onclick=magic;

}
window.onload = load;

function magic(){
	$("[name~='empName']").val("Alice");
	$("[name~='empAccount']").val("ck001583219@gmail.com");
	$("[name~='empPhone']").val("0953589679");
	$("[name~='empROCId']").val("H126301368");
	$("[name~='empAddress']").val("桃園縣楊梅區電研路226巷10號");
	$("[name~='empBirthDate']").val("1993-02-27");
}