/* Factorys */

angular.module('speechApp').factory('dataFactory', [ '$http', function($http) {
	var urlBase = '/speechservice';
	var dataFactory = {};
	
	dataFactory.getContestorsByContestGroupId = function(id) {
		return $http.get(urlBase+"/"+"contestor/contest_group/" + id);
	};
	
	dataFactory.getActivateContestContestGroups = function() {
		return $http.get(urlBase+"/"+"contest_group/onlyActivateContest");
	};
	
	dataFactory.getContestorsForJudgeToScore = function(idcontestGroup, idstaff, idrole) {
		return $http.get(urlBase+"/contestor"+"/contest_group/"+idcontestGroup+"/staff/"+idstaff+"/role/"+idrole);
	};
	
	dataFactory.getSelectContestGroupListForLoginedStaff = function(idstaff) {
		return $http.get(urlBase+"/"+"contest_group/staff/" + idstaff);
	};
	
	dataFactory.getSelectLoginStaffList = function() {
		return $http.get(urlBase+"/"+"staff/loginList");
	};

	dataFactory.getAllRecords = function(tableName) {
		return $http.get(urlBase+"/"+tableName);
	};
	
	dataFactory.deleteRecord = function(id, tableName){
		return $http.delete(urlBase+"/"+tableName+"/"+id);
	};
	
	dataFactory.insertRecord = function (record, tableName) {
	        return $http.post(urlBase+"/"+tableName , record);
	};
	
	dataFactory.login = function (staff) {
        return $http.post(urlBase+"/staff/login" , staff);
};

	dataFactory.updateRecord = function (record, tableName) {
	        return $http.put(urlBase+"/"+tableName , record);
	};
	return dataFactory;

} ]).constant('SW_DELAI', 100)
.factory('stopwatch', function (SW_DELAI,$timeout) {
    var data = { 
    		isAlertRaised: false,
    		alertThreshold: 0,
    		alertTime: 0,    		
    		start: 0,
    		isRunning: false,
            value: 0,
            m: "00",
            s: "00",
            ms: "0",
            intM: 0,
            intS: 0,
            laps: []
        },
        stopwatch = null; 
    var start = function (alertTimeIn = 0, alertThresholdIn = 0) {
    	data.alertTime = alertTimeIn;
    	data.alertThreshold = alertThresholdIn; 
    if ( !data.isRunning ){
    	data.isRunning = true;
    }
    if( 0 == data.start ){
		data.start = new Date().getTime()/100;
	}
        var current = new Date().getTime()/100;
        stopwatch = $timeout(function() {
            data.value = current - data.start;
            pushToHMS();
            start(data.alertTime, data.alertThreshold);
        }, SW_DELAI);
    };

    var stop = function () {
        $timeout.cancel(stopwatch);
        data.isRunning = false;
        stopwatch = null;
        data.isAlertRaised = false;
        data.alertTime = 0;
        data.alertThreshold = 0;
    };
    
    var pad = function (num, size) {
    	var s = "0000" + num;
    	return s.substr(s.length - size);
    }
    
    var pushToHMS = function () {
    	var divid = SW_DELAI / 10;
        var time = data.value;
        data.intM = Math.floor( time / (60 * divid) );
    	data.m = pad(data.intM,2);
    	time = time % (60 * divid);
    	data.intS = Math.floor( time / divid );
    	data.s = pad(data.intS,2);
    	data.ms = Math.floor(time % divid);
    	if ( 0 != data.alertTime )
    	{
    		if ( (time/divid) + data.alertThreshold >= data.alertTime)
    		{
    			data.isAlertRaised = true;
    		}
    		else{
    			data.isAlertRaised = false;
    		}
    	}
    }

    var reset = function () {
        stop();
        data.start = 0;
        data.value = 0;
        pushToHMS();
        data.laps = [];
    };

    var lap = function () {
        data.laps.push(data.value);
    };

    return {
        data: data,
        start: start,
        stop: stop,
        reset: reset,
        lap: lap
    };
});