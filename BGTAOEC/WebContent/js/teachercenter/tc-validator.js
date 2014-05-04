var oec = oec||{};
oec.tc = function(){};
//校验章/节
oec.tc.checkSection = function(section){
	if (this.isEmpty(section)) {
		return false;
	}
	var secArray =  section.split(":");
	if (secArray.length==1) {
		section = secArray[0];
	}
	if (secArray.length==2) {
		section = secArray[1];
	}
	if (section.length>80) {
		return false;
	}
	return true;
}
oec.tc.isEmpty = function (data){
	if (data==null|| typeof(data)!="undefined" && data=="")
	{
	    return true;;
	}
	return false;
}