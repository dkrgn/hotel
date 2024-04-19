# Hotel Project (Spring Boot Microservice Architecture)

This project implements simple logic of Hotel services. User is able to login/register, edit/delete account, create/edit/delete bookings. The project was implemented to suit Kubernetes deployment. The project can as well be executed with docker-compose on `7ae4087` commit and can be executed as plain Spring Boot services on `e30e68d` commit.
> Note: Eureka discovery service is included only in docker and plain spring boot versions. In the latest commit with k8s deployment Eureka is excluded (not needed).
> Note: client service is deployed neither in k8s nor in docker compose.

## Steps to deploy app in K8s

1. Start minikube
2. Apply all the files present in `/hotel/k8s` directory.
> Note: it is preferable to deploy the `config` and `secret` files first, then `postgres`, so the spring boot applications will not deploy faster then their respective databases.
4. Check that all the pods are in `Running` state without any errors.
> Note: `room` database must be populated manually with sql. Below is tutorial on how to do it.
  - As soon as the `pg-room-...` pod is deployed, execute the following command `kubectl exec -it <pod-name> -- bash`, where instead of `<pod-name>` you should put name of the pod (this can be achieved with  `kubectl get pods` command).
  -   Next, update packages with command `apt-get update` and install sudo with command `apt-get install sudo`.
  -   Next, execute command `sudo -i -u postgres` to switch to  `postgres` user.
  -   Next, execute `psql` command to connect to the postgres, after that execute `\l` to list all the databases and verify that  `rooms` database is present.
  -   Next, execute `\c rooms` to switch to the database.
  -   The last step is to execute some dump data for testing:
```
insert into rooms (id, description, room_number, type, price, capacity, is_available)
values
    (1, 'description', '1A', 'ORDINARY', 25.0, 2, true),
    (2, 'description', '2A', 'ORDINARY', 25.0, 2, true),
    (3, 'description', '3A', 'ORDINARY', 25.0, 2, true),
    (4, 'description', '4A', 'VIP', 50.0, 4, true),
    (5, 'description', '5A', 'VIP', 50.0, 4, true),
    (6, 'description', '6A', 'LUXURY', 100.0, 6, true);
```
  - To exit postgres, use `\q`, to exit postgres user and postgres container use `exit`.
5. Next, as soon as api-gateway pod is deployed and is in `Running` state, execute command `minikube service api-gateway-svc`. This command would tunnel the connection onto new port, which will be printed in the console. Copy this port as it will be needed in later steps. 
6. Open client-service directory.
7. Navigate to `/src/index.js` file.
8. Change port in line `axios.defaults.baseURL = "http://localhost:<host>"` instead of `<host>`.
9. Run `npm start`.
> Note: to execute this command, `node.js` should be installed to your system. More information can be found [here](https://nodejs.org/en/download).
10. Naviagate to `localhost:3000`.
11. If everything was correct, the page should load the home page with 6 rooms. 

## General Information

This app is built with support of Spring Boot frameworks, main dependencies are SpringJpa, SpringCloud, SpringWebFlux, Resilience4J, SpringEureka (not used in k8s deployment), PostgreSQL.
The overall structure consists of: 
1. API gateway that is the entrypoint for the backend part of the project.
2. Hotel service that plays a role of an orchestration service that aggregates all the necessary data and manages other services.
3. Auth service that mangages user login/register functionalities, it as well relates to the database `tokens` to store user tokens to manage active sessions.
4. User/Room/Payment/Notification/Booking services are all similar in their implementation and functionality. All of them contain CRUD operations to manage the entities, and all of them have databases as well, i.e. `users`, `rooms`, `payments`, `notifications`, and `bookings` respectively.
5. Client service is implemented with plain React.js and Axios.
