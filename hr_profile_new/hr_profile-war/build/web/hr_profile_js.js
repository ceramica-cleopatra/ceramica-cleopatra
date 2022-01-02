function handle_date(date)
{
var x=document.getElementById(date);
if(x.value.length==10)
{
 var y=x.value.substr(2,1);
 x.value=x.value.replace(y,"/");
 x.value=x.value.replace(y,"/");
}
else if(x.value.indexOf(".")>-1)
{
 var a=checkNumber(x.value.substr(0,x.value.substr(0,x.value.indexOf(".")).length));
 var b=checkNumber(x.value.substr(x.value.indexOf(".")+1,x.value.lastIndexOf(".")-x.value.indexOf(".")-1));
 var c=x.value.substr(x.value.lastIndexOf(".")+1);
 x.value=a.concat("/",b,"/",c);
}
else if(x.value.indexOf("-")>-1)
{
 a=checkNumber(x.value.substr(0,x.value.substr(0,x.value.indexOf("-")).length));
 b=checkNumber(x.value.substr(x.value.indexOf("-")+1,x.value.lastIndexOf("-")-x.value.indexOf("-")-1));
 c=x.value.substr(x.value.lastIndexOf("-")+1);
 x.value=a.concat("/",b,"/",c);
}
else if(x.value.indexOf("/")>-1)
{
 a=checkNumber(x.value.substr(0,x.value.substr(0,x.value.indexOf("/")).length));
 b=checkNumber(x.value.substr(x.value.indexOf("/")+1,x.value.lastIndexOf("/")-x.value.indexOf("/")-1));
 c=x.value.substr(x.value.lastIndexOf("/")+1);
 x.value=a.concat("/",b,"/",c);
}
else if(x.value.indexOf(",")>-1)
{
 a=checkNumber(x.value.substr(0,x.value.substr(0,x.value.indexOf(",")).length));
 b=checkNumber(x.value.substr(x.value.indexOf(",")+1,x.value.lastIndexOf(",")-x.value.indexOf(",")-1));
 c=x.value.substr(x.value.lastIndexOf(",")+1);
 x.value=a.concat("/",b,"/",c);
}
else if(x.value.length==8)
{
 x.value=x.value.substr(0,2).concat("/",x.value.substr(2,2),"/",x.value.substr(4));
}
document.getElementById(date).value=x.value;
return null;
}

function checkNumber(i)
{
if (10>i)
{
 i="0" + i;
}
 return i;
}

function handleEnter (field, event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
            var i;
            for (i = 0; field.form.elements.length > i ; i++)
                    if (field == field.form.elements[i])
                            break;
            i = (i + 1) % field.form.elements.length;
            field.form.elements[i].focus();
            return false;
    }
    else
    return true;
}

function getDaysNo()
{
 var frm_date=document.getElementById("frm:frm_date");
 var to_date=document.getElementById("frm:to_date");
 if(frm_date.value.length>0 && to_date.value.length>0)
 {var x=new Date(frm_date.value.substr(6,4),frm_date.value.substr(3,2)-1,frm_date.value.substr(0,2));
 var y=new Date(to_date.value.substr(6,4),to_date.value.substr(3,2)-1,to_date.value.substr(0,2));
 document.getElementById("frm:i").value=Math.ceil((y.getTime()-x.getTime())/(1000*60*60*24)+1);
 if(document.getElementById("frm:i").value<0)
 {
  document.getElementById("frm:i").value='';
 }
 }
}
