	//定义资源数据类型
    Ext.define('resource',{
    	extend: 'Ext.data.Model',
    	fields:[
    		{name:'resourceId',type:'int'},
    		{name:'resourceName'},
    		{name:'resourceType'},
    		{name:'resourceDesc'},
    		{name:'url'}
		]
    });
	
	//定义部门数据类型，用于下拉列表
	Ext.define('role', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'roleId',type:'int'},
        	{name:'roleName'},
        	{name:'roleDesc'}
    	]
	});
    
    //定义员工数据类型，作为下拉列表框
    Ext.define('roleForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'roleId'},
        	{name:'roleName'}
    	]
	});
    
	//定义部门数据类型
	Ext.define('department',{
		extend: 'Ext.data.Model',
		fields:[
			{name: 'departmentId', type: 'int'},
			{name:'departmentName'},
			{name:'createTime',type: 'date', dateFormat:'Y-m-d'},
			{name:'managerId'},
			{name:'managerName'},
			{name:'totalStaff'},
			{name:'description'}
		]
	});
	
	//定义部门数据类型，用于下拉列表
	Ext.define('deptForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'departmentId'},
        	{name:'departmentName'}
    	]
	});
	
	//定义员工数据类型
    Ext.define('staff', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'staffId', type: 'int'},
            {name: 'photoImg'},
            {name: 'photo'},
            {name: 'staffName'},
            {name: 'departmentId', type: 'int'},
            {name: 'departmentName'},
            {name: 'position'},
            {name: 'entryTime', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'phone'},
            {name: 'roleId', type: 'int'},
            {name: 'roleName'},
            {name: 'userName'},
            {name: 'password'},
            {name: 'gender'},
            {name: 'age', type: 'int'},
            {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'nationality'},
            {name: 'politicalStatus'},
            {name: 'maritalStatus'},
            {name: 'nativePlace'},
            {name: 'idNo'},
            {name: 'passportNo'},
            {name: 'domicilePlace'},
            {name: 'dateOfRecruitment', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'hightestEdu'},
            {name: 'hightestDegree'},
            {name: 'graduateSchool'},
            {name: 'major'},
            {name: 'schoolSystem'},
            {name: 'currentAddress'},
            {name: 'urgentContact'},
            {name: 'email'},
            {name: 'zipCode'},
            {name: 'ucPhone'}
         ]
    });
	    
    //定义员工数据类型，用于下拉列表
    Ext.define('staffForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'staffId'},
        	{name:'staffName'}
    	]
	});
	
	//定义修改密码的类
	Ext.define('Password', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'oldPwd',type: 'string'},
            {name: 'newPwd_1',type: 'string'},
            {name: 'newPwd_2',type: 'string'}
        ]
    });

	
    Ext.define('cost', {
        extend: 'Ext.data.Model',
        fields: [
       	    {name: 'costId', type: 'int'},
            {name: 'recordDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'staffId', type: 'int'},
            {name: 'staffName',type:'string'},
            {name: 'executeDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'payWay',type:'string'},
            {name: 'currency',type:'string'},
            {name: 'money',type: 'float'},
			{name: 'costCountry',type:'string'},
			{name: 'costProvince',type:'string'},
			{name: 'costAddress',type:'string'},
			{name: 'costUnitName',type:'string'},
			{name: 'costContactName',type:'string'},
			{name: 'costContactPosition',type:'string'},
			{name: 'costContactPhone',type:'string'},
			{name: 'costContactEmail',type:'string'},
			{name: 'usage1',type:'string'},
			{name: 'description1',type:'string'},
			{name: 'departmentId',type:'int'}
         ]
    });
    

	// 定义表格的填充模型journal
	Ext.define('journal',{
		extend: 'Ext.data.Model',
		fields: [
		    {name:'workId',type:'int'},
            {name:'recordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'staffId',type:'int'},
            {name:'staffName',type:'string'},
	        {name:'executeDate',type:'date',dateFormat:'Y-m-d'},
	        {name:'operateMode',type:'string'},
	        {name:'unitName',type:'string'},
	        {name:'country',type:'string'},
	        {name:'province',type:'string'},
	        {name:'address',type:'string'},
	        {name:'contactObject',type:'string'},
	        {name:'level',type:'string'},
	        {name:'contactWay',type:'string'},
	        {name:'contactName',type:'string'},
	        {name:'contactPosition',type:'string'},
	        {name:'contactPhone',type:'string'},
	        {name:'contactEmail',type:'string'},
	        {name:'startTime',type:'string'},
	        {name:'endTime',type:'string'},
	        {name:'workContent',type:'string'}		
         ]
	})

    // 定义表格的填充模型report
    Ext.define('report',{
        extend: 'Ext.data.Model',
        fields: [
            {name:'reportId',type:'int'},
            {name:'staffId',type:'int'},
            {name:'staffName',type:'string'},
            {name:'departmentId',type:'int'},
            {name:'departmentName'},
            {name:'visitDate',type:'date',dateFormat:'Y-m-d'},
            {name:'visitPlace',type:'string'},
            {name:'visitAim',type:'string'},
            {name:'projectNo',type:'int'},
            {name:'recordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'customer',type:'string'},
            {name:'phone',type:'int'},
            {name:'email',type:'string'},
            {name:'company',type:'string'},
            {name:'ticket',type:'int'},
            {name:'accommodation',type:'int'},
            {name:'transportation',type:'int'},
            {name:'meals',type:'int'},
            {name:'subsidy',type:'int'},
            {name:'sum',type:'int'},
            {name:'number',type:'int'}
         ]
    })
	
     //定义部门数据类型
    Ext.define('department',{
    	extend: 'Ext.data.Model',
    	fields:[
    		{name:'departmentId',type:'int'},
    		{name:'departmentName'},
    		{name:'createTime',type: 'date', dateFormat:'Y-m-d'},
    		{name:'managerId'},
    		{name:'managerName'},
    		{name:'totalStaff'},
    		{name:'description'}
		]
    });   
    
    
    
    
    
    
    
    