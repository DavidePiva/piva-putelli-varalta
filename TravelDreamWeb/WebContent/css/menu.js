function start(){
	document.getElementById('boxleft1').style.display = "none";
	document.getElementById('boxleft2').style.display = "none";
}

function menuFunc(menuId)
{
	if(document.getElementById(menuId).style.display == "none")
	{
		start();
		document.getElementById(menuId).style.display = "block";
	}
	else
	{
		start();
	}
}