$(function() {
	
	   //文本框失去焦点后
    $('form :input').blur(function(){
         var $parent = $(this).parent();
         $parent.find(".formtips").remove();
         //验证用户名
         if( $(this).is('#username') ){
                if( this.value=="" || this.value.length <=0 ){
                    var errorMsg = '姓名不能为空.';
                    $parent.append('<img class="formtips onError" src="../image/error.png" width="15px" height="15px"/><span class="formtips onError">'+errorMsg+'</span>');
                    
                }else{
                    var okMsg = '输入正确.';
                    $parent.append('<img  class="formtips onSuccess" src="../image/checked.png" width="15px" height="15px"/><span class="formtips onSuccess">'+okMsg+'</span>');
                }
         }
         
         
         //验证密碼
         if( $(this).is('#password') ){
                if( this.value=="" || this.value.length < 6 ){
                    var errorMsg = '请输入至少6位的密碼.';
                    $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                }else{
                    var okMsg = '输入正确.';
                    $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
                }
         }
                  
         //验证邮件
         if( $(this).is('#email') ){
            if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
                  var errorMsg = '请输入正确的E-Mail地址.';
                  $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
            }else{
                  var okMsg = '输入正确.';
                  $parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
            }
         }

         
    }).keyup(function(){
       $(this).triggerHandler("blur");
    }).focus(function(){
         $(this).triggerHandler("blur");
    });//end blur

  //提交，最终验证。
    $('#send').click(function(){
           
    	$("form :input.required").trigger('blur');
        var numError = $('form .onError').length;
        if(numError){
               return false;
        } 
       	$.post("../user/regisiter",
        			   { username:$("#username").val(), 
        				password:$("#password").val(), 
        				email:$("#email").val()
        			   },
        			   function(data){
        				  /**
        				   * {"success":true,"data":{"state":3,"stateInfo":"注册成功未激活"},"error":""}
        				   */
        				   var jsonStr=JSON.stringify(data);
        				   var obj = JSON.parse(jsonStr);
        				   alert("数据: \n" + obj.data.stateInfo ); 
        			   }	   
        );      
    });

   //重置
    $('#res').click(function(){
           $(".formtips").remove(); 
    });

});