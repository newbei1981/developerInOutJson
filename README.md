# developerInOutJson
                    A project that allows you to store and change data about the developer in json format
Developer information is stored in 3 json files: developer, account, skill. 
    
Example recording:
           developer.json       [{"id":3,"name":"Garila","accountId":2,"skillId":[1,2]}]
           account.json         [{"id":2,"login":"koka","password":"kola","accountStatus":"ACTIVE"}]
           skill.json           [{"id":1,"name":"python"},{"id":2,"name":"java"},{"id":3,"name":"c#"},{"id":4,"name":"php"}]
         

It is possible to add and modify a record. 
id are generated automatically. 

When deleted, information is deleted only superficially. Those. skills and Account information are deleted. This is done to enable statistics.
