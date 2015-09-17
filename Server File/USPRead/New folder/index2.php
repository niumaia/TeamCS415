<?php

/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data

  /**
 * check for POST request
 Alvindra Dutt 3/5/15 CS424
 
 */

if (isset($_POST['tag']) && $_POST['tag'] != '') {
	
	// get tag
    $tag = $_POST['tag'];
	
	 // include db handler
    require_once 'include/DB_Functions.php';
	$db = new DB_Functions();
	
	 // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
	
	//for login
	if ($tag == 'login'){
		// Request type is check Login
        $user_id = $_POST['user_id'];
        $password = $_POST['user_password'];
		
		
        // check for user
        $user = $db->getUserByEmailAndPassword($user_id,$password );
		//$event = $event_db->getEventDetails();
		
		if ($user !=false){
			// user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["user_id"] = $user["user_id"];
			//$response["user"]["user_id"] = $user["user_id"];
			$response["user"]["user_fname"] = $user["user_fname"];
			$response["user"]["user_lname"] = $user["user_lname"];
			$response["user"]["user_dob"] = $user["user_dob"];
			$response["user"]["user_gender"] = $user["user_gender"];
            $response["user"]["user_address"] = $user["user_address"];
			$response["user"]["user_contact"] = $user["user_contact"];
			$response["user"]["user_country"] = $user["user_country"];
			$response["user"]["user_email"] = $user["user_email"];	
			$response["user"]["user_role"] = $user["user_role"];			
            echo json_encode($response);
		} else {
			// user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect email or password!";
            echo json_encode($response);			
		}
		 
	}else if ($tag == 'blood_type_test'){
		$blood_type = $_POST['blood_type'];	
		$user_id = $_POST['user_id'];
		
		$user = $db->getBloodTypeData($user_id);
		
		
		//select distinct  u.user_name , b.description , r.test_id , b.blood_type from result r join blood_type b on r.blood_id = b.blood_id join user u on u.user_id = r.user_id where u.user_id = "120075274"	if($user){
		//TO DO dbfunctions 
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["bloodtype_result"]["typeresult_id"] = $user["typeresult_id"];
			$response["bloodtype_result"]["typeresult_date"] = $user["typeresult_date"];
			$response["bloodtype_result"]["typeresult_value"] = $user["typeresult_value"];
			$response["bloodtype_result"]["type_desc"] = $user["type_desc"];
			
			
			
			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	}
	else if ($tag == 'blood_culture_test'){
		//select bcr.cultureresult_id, bcr.cultureresult_date,bcr.cultureresult_value , bct.culture_meaning,bct.culture_cause,bct.culture_risk,bct.culture_treatment from bloodculture_result bcr join blood_culture_test bct on bcr.culture_id = bct.culture_id where bcr.user_id = '123456'
		$user_id = $_POST['user_id'];
		
		$user = $db->getBloodCultureData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["bloodculture_result"]["cultureresult_id"] = $user["cultureresult_id"];
			$response["bloodculture_result"]["cultureresult_date"] = $user["cultureresult_date"];
			$response["bloodculture_result"]["cultureresult_value"] = $user["cultureresult_value"];
			$response["bloodculture_result"]["culture_meaning"] = $user["culture_meaning"];
			$response["bloodculture_result"]["culture_cause"] = $user["culture_cause"];
			$response["bloodculture_result"]["culture_risk"] = $user["culture_risk"];
			$response["bloodculture_result"]["culture_treatment"] = $user["culture_treatment"];

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	
	
	}
	
	else if ($tag == 'blood_cholesterol_test'){
		$user_id = $_POST['user_id'];
		
		$user = $db->getBloodCholesterolData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["cholesterol_result"]["cholesterolresult_id"] = $user["cholesterolresult_id"];
			$response["cholesterol_result"]["cholesterolresult_date"] = $user["cholesterolresult_date"];
			$response["cholesterol_result"]["cholesterolresult_value"] = $user["cholesterolresult_value"];
			//$response["cholesterol_result"]["cholesterol_id"] = $user["cholesterol_id"];
			$response["cholesterol_result"]["cholesterol_range"] = $user["cholesterol_range"];
			$response["cholesterol_result"]["cholesterol_meaning"] = $user["cholesterol_meaning"];
			$response["cholesterol_result"]["cholesterol_cause"] = $user["cholesterol_cause"];
			$response["cholesterol_result"]["cholesterol_risk"] = $user["cholesterol_risk"];
			$response["cholesterol_result"]["cholesterol_treatment"] = $user["cholesterol_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	}
	else if ($tag == 'liver_test'){
		
		$user_id = $_POST['user_id'];
		
		$user = $db->getLiverData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["liver_result"]["liverresult_id"] = $user["liverresult_id"];
			$response["liver_result"]["liverresult_date"] = $user["liverresult_date"];
			$response["liver_result"]["liverresult_value"] = $user["liverresult_value"];
			//$response["cholesterol_result"]["cholesterol_id"] = $user["cholesterol_id"];
			$response["liver_result"]["liver_enzyme"] = $user["liver_enzyme"];
			$response["liver_result"]["liver_range"] = $user["liver_range"];
			$response["liver_result"]["liver_meaning"] = $user["liver_meaning"];
			$response["liver_result"]["liver_cause"] = $user["liver_cause"];
			$response["liver_result"]["liver_risk"] = $user["liver_risk"];
			$response["liver_result"]["liver_treatment"] = $user["liver_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	}
	else if ($tag == 'blood_glucose_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getGlucoseData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["glucose_result"]["glucoseresult_id"] = $user["glucoseresult_id"];
			$response["glucose_result"]["glucoseresult_date"] = $user["glucoseresult_date"];
			$response["glucose_result"]["glucoseresult_value"] = $user["glucoseresult_value"];			
			$response["glucose_result"]["glucose_type"] = $user["glucose_type"];
			$response["glucose_result"]["glucose_age"] = $user["glucose_age"];
			$response["glucose_result"]["glucose_range"] = $user["glucose_range"];
			
			$response["glucose_result"]["glucose_meaning"] = $user["glucose_meaning"];
			$response["glucose_result"]["glucose_risk"] = $user["glucose_risk"];
			
			$response["glucose_result"]["glucose_cause"] = $user["glucose_cause"];
			$response["glucose_result"]["glucose_treatment"] = $user["glucose_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'hiv_test'){
		$user_id = $_POST['user_id'];
		
		$user = $db->getHivData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["hiv_result"]["hivresult_id"] = $user["hivresult_id"];
			$response["hiv_result"]["hivresult_date"] = $user["hivresult_date"];
			$response["hiv_result"]["hivresult_value"] = $user["hivresult_value"];				
			$response["hiv_result"]["hiv_causes"] = $user["hiv_causes"];
			$response["hiv_result"]["hiv_treatment"] = $user["hiv_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	}
	else if ($tag == 'dengue_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getDengueData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["dengue_result"]["dengue_result_id"] = $user["dengue_result_id"];
			$response["dengue_result"]["dengue_result_date"] = $user["dengue_result_date"];
			$response["dengue_result"]["dengue_value"] = $user["dengue_value"];				
			$response["dengue_result"]["dengue_causes"] = $user["dengue_causes"];
			$response["dengue_result"]["dengue_treatment"] = $user["dengue_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	//
	else if ($tag == 'gas_test1')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getGas1Data($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["gas_test1"]["gasresult_id"] = $user["gasresult_id"];
			$response["gas_test1"]["gasresult_date"] = $user["gasresult_date"];
			$response["gas_test1"]["gas_oxy_val"] = $user["gas_oxy_val"];				
			$response["gas_test1"]["gas_co_val"] = $user["gas_co_val"];
			$response["gas_test1"]["gas_range"] = $user["gas_range"];
			$response["gas_test1"]["gas_cause"] = $user["gas_cause"];
			$response["gas_test1"]["gas_treatment"] = $user["gas_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'gas_test2')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getGas2Data($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["gas_test2"]["gasresult_id"] = $user["gasresult_id"];
			$response["gas_test2"]["gasresult_date"] = $user["gasresult_date"];
			$response["gas_test2"]["gas_ph_val"] = $user["gas_ph_val"];				
			$response["gas_test2"]["gas_range"] = $user["gas_range"];
			$response["gas_test2"]["gas_cause"] = $user["gas_cause"];
			$response["gas_test2"]["gas_treatment"] = $user["gas_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'sodium_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getSodiumData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["sodium_Test"]["electrolyteresult_id"] = $user["electrolyteresult_id"];
			$response["sodium_Test"]["electrolyteresult_date"] = $user["electrolyteresult_date"];
			$response["sodium_Test"]["elect_sod_val"] = $user["elect_sod_val"];				
			$response["sodium_Test"]["electrolyte_meaning"] = $user["electrolyte_meaning"];
			$response["sodium_Test"]["electrolyte_cause"] = $user["electrolyte_cause"];
			$response["sodium_Test"]["electrolyte_treatment"] = $user["electrolyte_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'potassium_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getPotassiumData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["potassium_test"]["electrolyteresult_id"] = $user["electrolyteresult_id"];
			$response["potassium_test"]["electrolyteresult_date"] = $user["electrolyteresult_date"];
			$response["potassium_test"]["elect_pot_val"] = $user["elect_pot_val"];				
			$response["potassium_test"]["electrolyte_meaning"] = $user["electrolyte_meaning"];
			$response["potassium_test"]["electrolyte_cause"] = $user["electrolyte_cause"];
			$response["potassium_test"]["electrolyte_treatment"] = $user["electrolyte_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'chloride_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getChlorideData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["chloride_test"]["electrolyteresult_id"] = $user["electrolyteresult_id"];
			$response["chloride_test"]["electrolyteresult_date"] = $user["electrolyteresult_date"];
			$response["chloride_test"]["elect_chl_val"] = $user["elect_chl_val"];				
			$response["chloride_test"]["electrolyte_meaning"] = $user["electrolyte_meaning"];
			$response["chloride_test"]["electrolyte_cause"] = $user["electrolyte_cause"];
			$response["chloride_test"]["electrolyte_treatment"] = $user["electrolyte_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'esr_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getESRData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["esr_test"]["esrresult_id"] = $user["esrresult_id"];
			$response["esr_test"]["esrresult_date"] = $user["esrresult_date"];
			$response["esr_test"]["esrresult_value"] = $user["esrresult_value"];				
			$response["esr_test"]["esr_meaning"] = $user["esr_meaning"];
			$response["esr_test"]["esr_cause"] = $user["esr_cause"];
			$response["esr_test"]["esr_treatment"] = $user["esr_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	
	else if ($tag == 'fbc_rbc_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getFBC_RBCData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["fbc_rbc_test"]["fbcresult_id"] = $user["fbcresult_id"];
			$response["fbc_rbc_test"]["fbcresult_date"] = $user["fbcresult_date"];
			$response["fbc_rbc_test"]["rbc_val"] = $user["rbc_val"];				
			$response["fbc_rbc_test"]["fbc_meaning"] = $user["fbc_meaning"];
			$response["fbc_rbc_test"]["fbc_cause"] = $user["fbc_cause"];
			$response["fbc_rbc_test"]["fbc_treatment"] = $user["fbc_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'fbc_wbc_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getFBC_WBCData($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["fbc_wbc_test"]["fbcresult_id"] = $user["fbcresult_id"];
			$response["fbc_wbc_test"]["fbcresult_date"] = $user["fbcresult_date"];
			$response["fbc_wbc_test"]["wbc_val"] = $user["wbc_val"];				
			$response["fbc_wbc_test"]["fbc_meaning"] = $user["fbc_meaning"];
			$response["fbc_wbc_test"]["fbc_cause"] = $user["fbc_cause"];
			$response["fbc_wbc_test"]["fbc_treatment"] = $user["fbc_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	else if ($tag == 'fbc_platelet_test')
	{
		$user_id = $_POST['user_id'];
		
		$user = $db->getFBC_Platelet_Data($user_id);
		
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["fbc_platelet_test"]["fbcresult_id"] = $user["fbcresult_id"];
			$response["fbc_platelet_test"]["fbcresult_date"] = $user["fbcresult_date"];
			$response["fbc_platelet_test"]["platelet_val"] = $user["platelet_val"];				
			$response["fbc_platelet_test"]["fbc_meaning"] = $user["fbc_meaning"];
			$response["fbc_platelet_test"]["fbc_cause"] = $user["fbc_cause"];
			$response["fbc_platelet_test"]["fbc_treatment"] = $user["fbc_treatment"];
			

			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
		
	}
	 else if ($tag == 'blood_glucose_graph') {
		$user_id = $_POST['user_id'];
		$response = array();
			$response["graph"] = array();
			$response["success"] = 1;
			$result = mysql_query("SELECT * FROM bloodglucose_result where user_id='4444' "); // Select all rows from fixture table
			while($row = mysql_fetch_array($result)){
				$tmp = array();        // temporary array to create single match information
				 
				$tmp["date"] = $row["glucoseresult_date"];
				$tmp["value"] = $row["glucoseresult_value"];
				
				
		
				array_push($response["graph"], $tmp);
			}
			
			echo json_encode($response);
	
	}
	
}else {
    echo "Access Denied";
}







?>