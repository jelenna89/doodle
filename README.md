#  Doodle

Instructions for deploy:

Warning: All steps must be executed as ordered and full path to json data must be provided. 


0. Add full path to data.json (there is file in project also, downloaded from task assignment
1. kubectl create configmap initial-data --from-file=data.json
2. kubectl apply -f postgres-storage.yaml 
3. kubectl apply -f postgres-configmap 
4. kubectl apply -f sql-init-scripts.yaml //this step will import data into db
5. kubectl apply -f postgres-deployment.yaml 
6. kubectl apply -f postgres-service.yaml




How to use:

get ip:port -> url = minikube service doodle --url
curl -v $(minikube service doodle --url)/doodle/users/John%20Doe/polls
curl -v $(minikube service doodle --url)/doodle/users/polls?text=John
curl -X GET -v $(minikube service doodle --url)/doodle/users/polls -d since="2017-01-25 12:30:01" 
curl -X GET -v $(minikube service doodle --url)/doodle/users/polls -d until="2017-01-25 12:30:01"  
curl -X GET -v $(minikube service doodle --url)/doodle/users/polls -d from="2017-01-25 00:00:00" -d to="2017-01-26 00:00:00" 


For improvement:
1. It was possible to solve postgres-*.yamls then doodle.yaml dependency with Readiness Probes, but there was not enough time.
2. I decided to put data in pg because it has good support for json and i was more comfortable with, but improvement is definitely nosql db.
3. User with space in path - bad solution. Also i prefer to have some business key, say userId, but I didn't have enough time to implement it
4. Spring boot, because you are using it and i wanted to try it for first time.
5. I couldn't find a way to search text with jsonb, so i used java streams. There is also one point to improve contains is case sensitive.
6. Also this time filter can be done on more sophisticated way
7. Tests...



Clean:
kubectl delete service postgres 
kubectl delete deployment postgres
kubectl delete configmap initial-data
kubectl delete configmap postgres-config
kubectl delete persistentvolumeclaim postgres-pv-claim
kubectl delete persistentvolume postgres-pv-volume
