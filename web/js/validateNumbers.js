/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



    function validateNo(i){
    var a;
     var x;
 
           x= document.getElementById("new_total_"+i);
//           alert(x.value);
//      alert("a"+x.value); 
 var numbers = /^\d+(?:\.\d+)?$/;  
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#new_total_"+i).css("border-color","#ff0000");
                $("#new_total_"+i).slideToggle("slow",function() {});
                $("#new_total_"+i).slideToggle("slow",function() {});   
                $("#new_total_"+i).focus();
           
      return false; 
      
     
    }
    
    }
   
}
    

    function validateActivity(i){
    var a;
     var x;
 
           x= document.getElementById("new_subtotal_"+i);
//           alert(x.value);
//      alert("a"+x.value); 
 var numbers = /^\d+(?:\.\d+)?$/;  
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#new_subtotal_"+i).css("border-color","#ff0000");
                $("#new_subtotal_"+i).slideToggle("slow",function() {});
                $("#new_subtotal_"+i).slideToggle("slow",function() {});   
                $("#new_subtotal_"+i).focus();
           
      return false; 
      
     
    }
    
    }}
    

    function validateWomen(i){
    var a;
     var x;
 
           x= document.getElementById("new_achievedw_"+i);
//           alert(x.value);
//      alert("a"+x.value); 
 var numbers = /^\d+(?:\.\d+)?$/;  
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#new_achievedw_"+i).css("border-color","#ff0000");
                $("#new_achievedw_"+i).slideToggle("slow",function() {});
                $("#new_achievedw_"+i).slideToggle("slow",function() {});   
                $("#new_achievedw_"+i).focus();
           
      return false; 
      
     
    }
    
    }}
    
    function validateMen(i){
    var a;
     var x;
 
           x= document.getElementById("new_achievedm_"+i);
//           alert(x.value);
//      alert("a"+x.value); 
 var numbers = /^\d+(?:\.\d+)?$/;  
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#new_achievedm_"+i).css("border-color","#ff0000");
                $("#new_achievedm_"+i).slideToggle("slow",function() {});
                $("#new_achievedm_"+i).slideToggle("slow",function() {});   
                $("#new_achievedm_"+i).focus();
           
      return false; 
      
     
    }
    
    }}
    
    function validateAchieved(i){
    var a;
     var x;
 
           x= document.getElementById("new_achievedtotal_"+i);
//           alert(x.value);
//      alert("a"+x.value); 
 var numbers = /^\d+(?:\.\d+)?$/;  
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#new_achievedtotal_"+i).css("border-color","#ff0000");
                $("#new_achievedtotal_"+i).slideToggle("slow",function() {});
                $("#new_achievedtotal_"+i).slideToggle("slow",function() {});   
                $("#new_achievedtotal_"+i).focus();
           
      return false; 
      
     
    }
    
    }}
    
