
apiVersion: v1
kind: Deployment
metadata:
     name: login-app-deployment
     labels:
          app: login-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: login-app
  template:
    metadata:
      labels:
        app: login-app
    spec:
      containers:
        - name: login-spring-boot
          image: login-spring-boot:1.0
          ports:
            - containerPort: 8080

        - name: login-angular
          image: login-angular:1.0
          ports:
            - containerPort: 8081