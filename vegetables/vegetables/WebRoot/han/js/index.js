 $(document).ready(function () {
            $("#imageFile").change(function () {


                var fileReader = new FileReader();
                fileReader.onload = function (e) {


                    var ingurl = e.target.result;
                    var preview = document.getElementById('imangid');
                    preview.src=e.target.result;
                };
                var imageFile = this.files[0];
                fileReader.readAsDataURL(imageFile);
            });
        });
 
 
 function details(id,name,title,price,details,picture){
	 
	 $(".details").toggleClass("on");
	 
	 $("#picimg").attr('src','http://www.hanchunyang.com/vagetables/'+picture); 
	 
	 $("#dname").html(name);
	 
	 $("#price").html(price);
	 
	 $("#title").html(title);
	 
	 $("#details").html(details);
	 
	 $("#ddddid").val(id);
 }
 
 function shouhui(){
	 
	 $(".details").toggleClass("on");
	 
	 
 }