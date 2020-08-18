<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- this UI prototype was inspired by this link below -->
<!-- https://www.w3schools.com/howto/howto_js_tabs.asp -->
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bobson.css" rel="stylesheet">
<!-- for jQuery DatePicker u have to include these 2 css files -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" type="text/javascript" charset="utf-8"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- for jQueryUI DatePicker , you need to include these 2 js files ... -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
function openCity(evt, cityName) {
	  // Declare all variables
	  var i, tabcontent, tablinks;

	  // Get all elements with class="tabcontent" and hide them
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }

	  // Get all elements with class="tablinks" and remove the class "active"
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }

	  // Show the current tab, and add an "active" class to the button that opened the tab
	  document.getElementById(cityName).style.display = "block";
	  evt.currentTarget.className += " active";
	}
</script>
</head>

<body>
<h2>Restful API 示例程式</h2>

<!-- Tab links -->
<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'tabOne')">用 id 取一個員工</button>
  <button class="tablinks" onclick="openCity(event, 'tabAll')">取 出 全 部 員 工</button>
  <button class="tablinks" onclick="openCity(event, 'tabSearch')">以 姓 名 搜 尋 員 工</button>
  <button class="tablinks" onclick="openCity(event, 'tabCreate')">新 增 員 工</button>
  <button class="tablinks" onclick="openCity(event, 'tabUpdate')">修 改 員 工</button>
  <button class="tablinks" onclick="openCity(event, 'tabDelete')">刪 除 員 工</button>
  <!-- <button class="tablinks" onclick="openCity(event, 'tabTest')">測 試 用 頁 面 </button> -->
  <!-- <button class="tablinks" onclick="openCity(event, 'tabMock')">測 試 偽 表 格 </button> -->
</div>

<!-- Tab content -->
<div id="tabOne" class="tabcontent">
  <h3>用 id 取一個員工</h3>
  <p>輸入id ... 返回一個員工 ....</p>
  
  <div id="oneEmployee">
     <input v-model="emp_id" type="number" @change="check_empId" placeholder="請輸入員工編號">
     <button id="refreshOne" v-on:click="ajaxForOne"> 查 詢 </button>     
     <table class="employees" v-if="status === 200 && result === true">
      <tr>
         <th> 編  號 </th>
         <th> 姓  名 </th>
         <th> 生  日 </th>
         <th> 地  址 </th>
         <th> 幸 運 號 </th>
         <th> 在職狀態 </th>
       </tr>
       <tr>
         <td> {{ employee.id }} </td>
         <td> {{ employee.name }} </td>
         <td> {{ employee.birthday }} </td>
         <td> {{ employee.address }} </td>
         <td> {{ employee.luckyNumber }} </td>
         <td> {{ employee.online }} </td>
       </tr>
     </table>
     
     <div v-if=" msg === 'EMPLOYEE_NOTFOUND' " class="err_red">
        找不到編號 {{ emp_id }} 的員工，請用其他員工編號重新搜尋
     </div>
     
     <div v-if="errors.length > 0" class="err_red">
        <b>請 處 理 以 下 發 生 的 錯 誤</b>
              <ul>
                <li v-for="error in errors">{{ error }}</li>
              </ul>
     </div>
     
  </div>
</div>

<div id="tabAll" class="tabcontent">
  <h3>取 出 全 部 員 工</h3>
  <p>取出全部的員工資料 ... 並繪製在這裡... </p>
 
  <div id="allEmployees">
     <button id="refreshAll" v-on:click="ajaxForAll"> 更 新 </button>
     <table class="employees">
      <tr>
         <th> 編  號 </th>
         <th> 姓  名 </th>
         <th> 生  日 </th>
         <th> 地  址 </th>
         <th> 幸 運 號 </th>
         <th> 在職狀態 </th>
       </tr>
       <tr v-for="employee in employees">
         <td> {{ employee.id }} </td>
         <td> {{ employee.name }} </td>
         <td> {{ employee.birthday }} </td>
         <td> {{ employee.address }} </td>
         <td> {{ employee.luckyNumber }} </td>
         <td> {{ employee.online }} </td>
       </tr>
     </table>
  </div>
  
</div>


<div id="tabSearch" class="tabcontent">
  <h3>以 姓 名 搜 尋 員 工</h3>
  <p>輸入部分姓名 ... 取出姓名裡符合關鍵字的所有員工 ... </p>
  
  <div id="searchEmployees">
      <input v-model="emp_name" placeholder="請輸入員工的姓名"/>
      <button id="searchBTN" v-on:click="ajaxForSearch"> 查 詢 </button>     
     <table class="employees" v-if="status === 200 && result">
      <tr>
         <th> 編  號 </th>
         <th> 姓  名 </th>
         <th> 生  日 </th>
         <th> 地  址 </th>
         <th> 幸 運 號 </th>
         <th> 在職狀態 </th>
       </tr>
       <tr v-for="employee in employees">
         <td> {{ employee.id }} </td>
         <td> {{ employee.name }} </td>
         <td> {{ employee.birthday }} </td>
         <td> {{ employee.address }} </td>
         <td> {{ employee.luckyNumber }} </td>
         <td> {{ employee.online }} </td>
       </tr>
     </table>
      
      <div v-if=" !result && msg === 'EMPLOYEE_NOTFOUND' && status === -99 " class="err_red">
        <p>找不到姓名裡有 [ {{ emp_name }} ] 的員工，請用其他關鍵字重新搜尋</p>
      </div>
      
      <div v-if="errors.length" class="err_red">
          <b>請 處 理 以 下 發 生 的 錯 誤</b>
          <ul>
            <li v-for="error in errors"> {{ error }} </li>
          </ul>
      </div>
      
      
  </div><!-- id="searchEmployees" -->
</div><!-- id="tabSearch" -->


<div id="tabCreate" class="tabcontent">
  <h3>新 增 員 工</h3>
  <p>新增一筆員工資料</p>
  
  <div id="createEmployee">
  <form
  id="createForm"
  @submit="ajaxForCreate"
  action="<%= request.getContextPath()%>/employee"
  method="post"
  >
  
  <p>
    <label for="e_create_name"> 姓 名 </label>
    <input
      id="e_create_name"
      v-model="emp_name"
      type="text"
      name="e_create_name"
      min="2"
    >
  </p>

  <p>
    <label for="e_create_address"> 地 址 </label>
    <input
      id="e_create_address"
      v-model="emp_address"
      type="text"
      name="e_create_address"
      min="10"
    >
  </p>

  <p>
    <label for="e_create_luckyNumber"> 幸 運 號 碼 </label>
    <input
      id="e_create_luckyNumber"
      v-model="emp_luckyNumber"
      type="number"
      name="e_create_luckyNumber"
      min="1"
      max="100"
    >
  </p>

  <p>
    <label for="e_create_datepicker"> 出 生 日 期 </label>
    <input v-model="emp_birthday" type="text" id="e_create_datepicker" readonly>
  </p>
  
  <!-- 
  <p>
    <label for="e_create_birthday"> 出 生 日 期 </label>
     <input
      id="e_create_birthday"
      v-model="emp_birthday"
      type="text"
      name="e_create_birthday"
    >
  </p>
  -->
  
  <!-- options組件的用法參考 -->
  <!-- https://stackoverflow.com/questions/45264923/how-to-return-boolean-and-not-string-when-using-select -->
  <p>
    <label for="movie">在 職 狀 態</label>
    <select v-model="emp_online">
          <option v-for="option in options" :value="option.value">{{option.text}}</option>
      </select>
  </p>

  <p>
    <input
      type="submit"
      value="新 增 員 工"
    >
  </p>
  
  <!-- https://vuejs.org/v2/cookbook/form-validation.html -->
  <!-- show error messages here -->
  <span v-if="result===false && status!==200" class="err_red">
        <p>
         新增員工失敗 <br/>
         錯誤代碼 : {{ status }} <br/>
         錯誤訊息 : {{ msg }} <br/>
         </p>
  </span>
  
  <span v-if="errors.length>0" class="err_red">
         <b>請 處 理 以 下 發 生 的 錯 誤</b>
         <ul>
              <li v-for="error in errors">{{ error }}</li>
         </ul>
  </span>
   
   <span v-if="result===true && status===200" class="info_blue">
        <p> 新 增 員 工 資 料 成 功</p>
   </span>
   
</form>
  
  
  </div>
</div>

<div id="tabUpdate" class="tabcontent">
  
  <h3>修 改 員 工</h3>
  <p>修改一筆員工資料</p>
  
  <div id="updateEmployee">
  <form
  id="updateForm"
  @submit="ajaxForUpdate"
  action="<%= request.getContextPath()%>/employee"
  method="post"
  >
  
  <p>
    <label for="e_update_name"> 員工編號 </label>
    <input
      id="e_update_id"
      v-model="emp_id"
      type="number"
      name="e_update_id"
      min="1"
    >
  </p>
  
  <p>
    <label for="e_update_name"> 姓 名 </label>
    <input
      id="e_update_name"
      v-model="emp_name"
      type="text"
      name="e_update_name"
      min="2"
    >
  </p>

  <p>
    <label for="e_update_address"> 地 址 </label>
    <input
      id="e_update_address"
      v-model="emp_address"
      type="text"
      name="e_update_address"
      min="10"
    >
  </p>

  <p>
    <label for="e_update_luckyNumber"> 幸 運 號 碼 </label>
    <input
      id="e_update_luckyNumber"
      v-model="emp_luckyNumber"
      type="number"
      name="e_update_luckyNumber"
      min="1"
      max="100"
    >
  </p>

  <p>
    <label for="e_update_datepicker"> 出 生 日 期 </label>
    <input v-model="emp_birthday" type="text" id="e_update_datepicker" readonly>
  </p>
  
  <!-- 
  <p>
    <label for="e_update_birthday"> 出 生 日 期 </label>
     <input
      id="e_update_birthday"
      v-model="emp_birthday"
      type="text"
      name="e_update_birthday"
    >
  </p>
  -->
  
  <!-- options組件的用法參考 -->
  <!-- https://stackoverflow.com/questions/45264923/how-to-return-boolean-and-not-string-when-using-select -->
  <p>
    <label for="movie">在 職 狀 態</label>
    <select v-model="emp_online">
          <option v-for="option in options" :value="option.value">{{option.text}}</option>
      </select>
  </p>

  <p>
    <input
      type="submit"
      value="修 改 員 工"
    >
  </p>
  
  <!-- https://vuejs.org/v2/cookbook/form-validation.html -->
  <!-- show error messages here -->
  <span v-if="result===false && status!==200" class="err_red">
        <p>
         修改員工失敗 <br/>
         錯誤代碼 : {{ status }} <br/>
         錯誤訊息 : {{ msg }} <br/>
         </p>
  </span>
  
  <span v-if="errors.length>0" class="err_red">
         <b>請 處 理 以 下 發 生 的 錯 誤</b>
         <ul>
              <li v-for="error in errors">{{ error }}</li>
         </ul>
  </span>
   
   <span v-if="result===true && status===200" class="info_blue">
        <p> 修 改 員 工 成 功</p>
   </span>
   
</form>
  </div><!-- id="updateEmployee" -->
  
</div>

<div id="tabDelete" class="tabcontent">
  <div id="deleteEmployee">
      <h3>刪 除 員 工</h3>
      <input v-model="emp_id" type="number" @change="check_empId" placeholder="請輸入要刪除的員工編號">
      <button id="deleteOne" v-on:click="ajaxForDelete"> 刪 除 </button>
      
      <span v-if="errors.length>0" class="err_red">
         <b>請 處 理 以 下 發 生 的 錯 誤</b>
         <ul>
              <li v-for="error in errors">{{ error }}</li>
         </ul>
      </span>
  
      <span v-if="result===true && status===200" class="info_blue">
            <p> 刪 除 員 工 資 料 成 功</p>
       </span>
       
       <span v-if="!result && status!==200" class="err_red">
            <p> 刪 除 員 工 資 料 失 敗</p>
            狀態碼: {{ status }} <br/>
            訊息: {{ msg }}<br/>
       </span>     
  </div><!-- id="deleteEmployee" -->
</div><!-- id="tabDelete" -->

<!-- switch it to class="tabcontent" if u wanna see this div -->
<div id="tabTest" class="disappear">
  <h3>測 試 用 頁 面</h3>
  <p>test ... test ... test ...</p>
  
  <div id="testResult">
  <h1>Bitcoin Price Index</h1>
      <div
        v-for="currency in info"
        class="currency"
      >
        {{ currency.description }}:
        <span class="lighten">
          <span v-html="currency.symbol"></span>{{ currency.rate_float | currencydecimal }}
        </span>
      </div><!-- end-v-for -->
      
      <!-- DatePicker test -->
      <p>Date: <input v-model="birthday" type="text" id="datepicker" readonly></p>
      <button id="testDatePickerBTN" v-on:click="ajaxForTestDatePicker"> 測試傳出的日期值 </button> 
  </div><!-- id="testResult" -->
  
</div>

<!-- switch it to class="tabcontent" if u wanna see this div -->
<div id="tabMock" class="disappear">
  <h3>偽 表 格</h3>
  <p>mock ... mock ... mock ...</p>
  
   <table class="employees">
       <tr>
         <th> 編  號 </th>
         <th> 姓  名 </th>
         <th> 生  日 </th>
         <th> 地  址 </th>
         <th> 幸 運 號 </th>
         <th> 在職狀態 </th>
       </tr>
       <tr>
         <td>1</td>
         <td>Alfreds Futterkiste</td>
         <td>Maria Anders</td>
         <td>Germany</td>
         <td>Germany</td>
         <td>Germany</td>
       </tr>
       <tr>
         <td>2</td>
         <td>Berglunds snabbköp</td>
         <td>Christina Berglund</td>
         <td>Sweden</td>
         <td>Sweden</td>
         <td>Sweden</td>
       </tr>
       <tr>
         <td>3</td>
         <td>Centro comercial Moctezuma</td>
         <td>Francisco Chang</td>
         <td>Mexico</td>
         <td>Mexico</td>
         <td>Mexico</td>
       </tr>
       <tr>
         <td>4</td>
         <td>Ernst Handel</td>
         <td>Roland Mendel</td>
         <td>Austria</td>
         <td>Austria</td>
         <td>Austria</td>
       </tr>
       <tr>
         <td>5</td>
         <td>Island Trading</td>
         <td>Helen Bennett</td>
         <td>UK</td>
         <td>UK</td>
         <td>UK</td>
       </tr>
       <tr>
         <td>6</td>
         <td>Königlich Essen</td>
         <td>Philip Cramer</td>
         <td>Germany</td>
         <td>Germany</td>
         <td>Germany</td>
       </tr>
       <tr>
         <td>7</td>
         <td>Laughing Bacchus Winecellars</td>
         <td>Yoshi Tannamuri</td>
         <td>Canada</td>
         <td>Canada</td>
         <td>Canada</td>
       </tr>
       <tr>
         <td>8</td>
         <td>Magazzini Alimentari Riuniti</td>
         <td>Giovanni Rovelli</td>
         <td>Italy</td>
         <td>Italy</td>
         <td>Italy</td>
       </tr>
       <tr>
         <td>9</td>
         <td>North/South</td>
         <td>Simon Crowther</td>
         <td>UK</td>
         <td>UK</td>
         <td>UK</td>
       </tr>
       <tr>
         <td>10</td>
         <td>Paris spécialités</td>
         <td>Marie Bertrand</td>
         <td>France</td>
         <td>France</td>
         <td>France</td>
       </tr>
     </table>
  
</div>

<!-- VUE.js codes wrote here were inspired by this link -->
<!-- https://vuejs.org/v2/cookbook/using-axios-to-consume-apis.html -->
<!-- js代碼要寫在最後面，讓頁面DOM元素先載入 -->
<script type="text/javascript">

/*
 * 
   解析的資料格式
 {
	   "USD":{
	      "code":"USD",
	      "symbol":"&#36;",
	      "rate":"11,286.5840",
	      "description":"United States Dollar",
	      "rate_float":11286.584
	   },
	   "GBP":{
	      "code":"GBP",
	      "symbol":"&pound;",
	      "rate":"8,624.8350",
	      "description":"British Pound Sterling",
	      "rate_float":8624.835
	   },
	   "EUR":{
	      "code":"EUR",
	      "symbol":"&euro;",
	      "rate":"9,603.8897",
	      "description":"Euro",
	      "rate_float":9603.8897
	   }
	}
 
 */
 
 /*
   定義VUE.js會用到的基礎類別，稍後會讓其他的物件繼承，需要被重用的函式寫在這裡
 */
 
 var base = {
	 created: function () {
	    this.hello()
	 },
	 methods: {
	    hello: function () {
	         console.log('hello from mixin!');
	    },
	    clearErrorCss: function () {
	    	console.log(' ===== clearErrorCss === ');
	    },
	    ajaxForAll : function() {
			axios
		      .get('<%= request.getContextPath()%>/employees')
		      .then(response => (this.employees = response.data.object));
			console.log('=== ajaxForAll ===');
		},
		ajaxForOne : function() {
			//clear errors list first
		    this.errors = [];
		    /* check id value first */
			if(this.emp_id > 0){
			   //ajax call
			   axios
		         .get('<%= request.getContextPath()%>/employee/' + this.emp_id)
		         .then(response => {
		       	     this.employee = response.data.object;
		    	     this.result = response.data.result;
		    	     this.status = response.data.status;
		    	     this.msg = response.data.msg;
		    	  });
			   } else {
				    // display error messages
				    this.result = false;  // show red error fonts
			        this.errors.push('請輸入員工編號');
			   }
			console.log('=== ajaxForOne === 取出編號 [' + this.emp_id + '] 的員工');
			console.log('=== ajaxForOne === result = [' + this.result + '] ');
			console.log('=== ajaxForOne === status = [' + this.status + '] ');
			
		},
		ajaxForSearch : function() {
			//clear errors list first
		    this.errors = [];
			
			/* check keyword length first */
			if(this.emp_name.length >= 2){
				    //ajax call
					axios
		      		.post('<%= request.getContextPath()%>/employees/search',{
		    	  		filters : [
		    		 		  {
		    		     		column : "name",
		    		     		operator : "like",
		    		    		value : this.emp_name
		    		 		  }
		    	  		],
		    	  		sorts : [
		    				  {
		    		  		    column : "name",
		    		    		type : "desc"
		    		 		  }
		    	 		],
		    	  		pageNumber : 1,
		    	  		pageSize : 10
		       		})
		      		.then(response => {
		    	 		this.employees = response.data.object;
		    	  		this.result = response.data.result;
		    	  		this.status = response.data.status;
		    	  		this.msg = response.data.msg;
		    		});
					
					console.log('=== ajaxForSearch === 搜尋姓名關鍵字 [' + this.emp_name + '] 的員工');
					console.log('=== ajaxForSearch === result = [' + this.result + '] ');
					console.log('=== ajaxForSearch === status = [' + this.status + '] ');
			} else {
				    // display error messages
				    this.result = false;  // show red error fonts
			        this.errors.push('需要輸入姓名，姓名長度至少為2個字元');
			}
		},
		ajaxForCreate : function(e) {
			  //clear errors list first
		      this.errors = [];
		
			  if (this.emp_name && this.emp_address && this.emp_luckyNumber) {

		        //ajax call
				  axios
			      .post('<%= request.getContextPath()%>/employee',{
			    	  name: this.emp_name,
			    	  address : this.emp_address,
			    	  birthday : this.emp_birthday,
			    	  luckyNumber : this.emp_luckyNumber,
			    	  online : this.emp_online
			       })
			      .then(response => {
			    	  this.msg = response.data.msg;
			    	  this.result = response.data.result;
			    	  this.status = response.data.status;
			    	});
		        
		      }else {

			      if (this.emp_name.length < 2) {
			        this.errors.push('需要輸入姓名，姓名長度至少為2個字元');
			      }
			      if (!this.emp_address) {
			        this.errors.push('需要輸入地址，地址長度最少為10個字');
			      }
			      if (!this.emp_luckyNumber) {
				        this.errors.push('幸運號碼需要介於1到100之間');
				  }
			      
			      //reset to default value for hiding blue ' 新 增 員 工 資 料 成 功 '
			      this.msg = '';
			      this.result = true;
			      this.status = null;
		      }
		      
		      //防止表單提交
		      e.preventDefault();
		      console.log('=== ajaxForCreate === > result === ' + this.result + '  status === ' + this.status);
		},
		ajaxForUpdate: function (e){
			
			//clear errors list first
		      this.errors = [];
		
			  if (this.emp_id && this.emp_name && this.emp_address && this.emp_luckyNumber) {

		        //ajax call
				  axios
			      .put('<%= request.getContextPath()%>/employee',{
			    	  id: this.emp_id,
			    	  name: this.emp_name,
			    	  address : this.emp_address,
			    	  birthday : this.emp_birthday,
			    	  luckyNumber : this.emp_luckyNumber,
			    	  online : this.emp_online
			       })
			      .then(response => {
			    	  this.msg = response.data.msg;
			    	  this.result = response.data.result;
			    	  this.status = response.data.status;
			    	});
		        
		      }else {
		    	  if (this.emp_id <= 0) {
				      this.errors.push('需要輸入大於0的員工編號');
				  }

			      if (this.emp_name.length < 2) {
			        this.errors.push('需要輸入姓名，姓名長度至少為2個字元');
			      }
			      if (!this.emp_address) {
			        this.errors.push('需要輸入地址，地址長度最少為10個字');
			      }
			      if (!this.emp_luckyNumber) {
				        this.errors.push('幸運號碼需要介於1到100之間');
				  }
			      
			      //reset to default value for hiding blue ' 修 改 員 工 資 料 成 功 '
			      this.msg = '';
			      this.result = true;
			      this.status = null;
		      }
		      
		      //防止表單提交
		      e.preventDefault();
		      console.log('=== ajaxForUpdate === > result === ' + this.result + '  status === ' + this.status);
		      console.log('=== ajaxForUpdate === > emp_id === ' + this.emp_id);
		      console.log('=== ajaxForUpdate === > emp_name === ' + this.emp_name);
		      console.log('=== ajaxForUpdate === > emp_address === ' + this.emp_address);
		      console.log('=== ajaxForUpdate === > emp_luckyNumber === ' + this.emp_luckyNumber);
		      console.log('=== ajaxForUpdate === > emp_birthday === ' + this.emp_birthday);
		      console.log('=== ajaxForUpdate === > emp_online === ' + this.emp_online);
		},
		ajaxForDelete: function () {
			//clear errors list first
		      this.errors = [];
		
			  if (this.emp_id > 0) {

		        //ajax call
				  axios
			      .delete('<%= request.getContextPath()%>/employee/' + this.emp_id)
			      .then(response => {
			    	  this.msg = response.data.msg;
			    	  this.result = response.data.result;
			    	  this.status = response.data.status;
			    	});
		        
		      }else {
		    	  
				  this.errors.push('需要輸入大於0的員工編號');

			      //reset to default value for hiding blue ' 刪 除 員 工 資 料 成 功 '
			      this.msg = '';
			      this.result = true;
			      this.status = null;
		      }
			
	         console.log(' ===== ajaxForDelete ===== ');
	         console.log(' ===== ajaxForDelete ===== emp_id === ' + this.emp_id);
	    },
	    ajaxForTestDatePicker: function () {
	    	console.log(' =====  ajaxForTestDatePicker ===== ');
	    	console.log('birthday = ' + this.birthday);
	    	
	    },
	    check_empId: function(){
	    	//use for limiting positive value input [強制輸入大於等於0的數字]
	        var emp_id = this.emp_id;
	             emp_id = emp_id.replace(/[^\d]/g, ''); // Clear characters other than "number" and "."
	        if (emp_id.indexOf('.') < 0 && emp_id != '') {
	                       // The above has been filtered. The control here is that if there is no decimal point, the first digit cannot be an amount similar to 01, 02.
	            emp_id = parseInt(emp_id);
	        }
	        this.emp_id = emp_id;
	    }
	    
     }//methods:
  }
 
 //用id取一個員工
 var vue_getOne = new Vue ({
	 mixins: [base],
	 el: "#oneEmployee",
	 data: {
		 errors : [],
		 emp_id: null,
		 result: true,
		 status: null,
		 msg: '',
		 employee: null
	 },
	 watch:{
	        /* 對 emp_id 值進行監聽，只要emp_id 改變了，就會觸發程式 */
	        emp_id: function (val) {
	            this.result = true; //消掉紅色錯字區域
	            this.msg = ''; //清空msg
	        }
	 }
 });
 
 
 //取出全部員工
 var vue_getAll = new Vue ({
	 mixins: [base],
     el: "#allEmployees",
     data: {
        employees: null,
        result: null,
        status: null
     },
     mounted: function() {
        console.log('== mounted == ');
        this.ajaxForAll();
		  /*
		  axios
	      .get('http://localhost:8080/apiez01/employees')
	      .then(response => (this.employees = response.data.object));
		  */
	    this.hello();
	}
	
 });
 
 //用姓名搜尋員工
 var vue_Search = new Vue ({
	 mixins: [base],
	 el: "#searchEmployees",
	 data: {
		 errors: [],
		 emp_name: '',
		 result: true,
		 status: 0,
		 msg: '',
		 employees: null
	 },
	 watch:{
	        /* 對emp_name值進行監聽，只要emp_name改變了，就會觸發程式 */
	        emp_name: function (val) {
	            this.result = true; //消掉紅色錯字區域
	            this.msg = ''; //清空msg
	            this.status = 0;
	        }
	 }
	 
 });
 
 //新增員工
 var vue_Create = new Vue ({
	 mixins: [base],
	 el: "#createForm",
	 data: {
		  errors: [],
		  options: [
		         {text: '在職中', value: true},
			     {text: '已離職', value: false},
		  ],
		  emp_name: '',
		  emp_address: '',
		  emp_luckyNumber: '',
		  emp_birthday: '1980-01-01',
		  emp_online: 'true',
		  msg : '',
		  result: true,
		  status: null
	 },
	 mounted: function(){
		 /* this part below is for initialing DatePicker*/
	     var vm = this;
         $('#e_create_datepicker').datepicker({
        	// 這裡是'yy-mm-dd'沒有錯，如果打成'yyyy-mm-dd'它會出現 20202020-08-06 這樣子把年份重複兩次
        	  dateFormat: 'yy-mm-dd',
        	  changeYear: true,
        	  yearRange: "-90:+0", // last 90 years
              onSelect: function(dateText) { 
                   vm.emp_birthday = dateText
              }
         });
	 }
 
 });
 
 
//修改員工
 var vue_Update = new Vue ({
	 mixins: [base],
	 el: "#updateForm",
	 data: {
		  errors: [],
		  options: [
		         {text: '在職中', value: true},
			     {text: '已離職', value: false},
		  ],
		  emp_id: 0,
		  emp_name: '',
		  emp_address: '',
		  emp_luckyNumber: '',
		  emp_birthday: '1980-08-06',
		  emp_online: 'true',
		  msg : '',
		  result: true,
		  status: null
	 },
	 mounted: function(){
		 /* this part below is for initialing DatePicker*/
	     var vm = this;
         $('#e_update_datepicker').datepicker({
        	// 這裡是'yy-mm-dd'沒有錯，如果打成'yyyy-mm-dd'它會出現 20202020-08-06 這樣子把年份重複兩次
        	  dateFormat: 'yy-mm-dd',
        	  changeYear: true,
        	  yearRange: "-90:+0", // last 90 years
              onSelect: function(dateText) { 
                   vm.emp_birthday = dateText
              }
         });
	 }
 
 });
 
 
//刪除員工
 var vue_Delete = new Vue ({
	 mixins: [base],
	 el: "#deleteEmployee",
	 data: {
		  errors: [],
		  emp_id: 0,
		  msg : '',
		  result: true,
		  status: null
	 },
     watch:{
	        /* 對 emp_id 值進行監聽，只要emp_id 改變了，就會觸發程式 */
	        emp_id: function (val) {
	            this.errors = []; //消掉紅色錯字區域
	            this.result = true;
	            this.msg = ''; 
	        }
	 }
 
 });

/* DatePicker 的問題可能解法 
 * https://stackoverflow.com/questions/41200729/vue-js-and-jquery-datepicker-timepicker-two-way-binding
 */

 //var vue_testResult = new Vue({
//	  mixins: [base],
//	  el: '#testResult',
//	  data : {
//		  info: null,
//		  birthday: '1988-08-08'
//	  },
//	  /*
//	  data()  {
//		  return {
//			  info: null,
//			  birthday: '1988-08-08'
//		  }
//	  },
//	  */
//	  mounted: function(){
//		  console.log('== mounted ==');
//		  axios
//	      .get('https://api.coindesk.com/v1/bpi/currentprice.json')
//	      .then(response => (this.info = response.data.bpi));
//		  /* this part below is for DatePicker*/
//		     var vm = this;
 //            $('#datepicker').datepicker({
            	 // 這裡是'yy-mm-dd'沒有錯，如果打成'yyyy-mm-dd'它會出現 20202020-08-06 這樣子把年份重複兩次
//            	  dateFormat: 'yy-mm-dd',
//                  onSelect: function(dateText) { 
//                       vm.birthday = dateText
//                  }
//             });
		  
//	  },filters: {
//		  currencydecimal (value) {
//			    return value.toFixed(2)
//         }
//     }
	  
//  });

 
</script>


</body>
</html>
