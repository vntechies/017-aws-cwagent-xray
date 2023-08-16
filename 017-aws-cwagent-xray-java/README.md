# Amazon CloudWatch for AWS X-Ray

## Before Build

- This project use JDK 17, 

- In order to download JDK 17 in Linux 2, please follow below steps

```
wget https://download.java.net/java/GA/jdk17/0d483333a00540d886896bac774ff48b/35/GPL/openjdk-17_linux-x64_bin.tar.gz

tar xvf openjdk-17_linux-x64_bin.tar.gz

sudo mv jdk-17 /opt/

sudo tee /etc/profile.d/jdk.sh <<EOF
export JAVA_HOME=/opt/jdk-17
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

source /etc/profile.d/jdk.sh

echo $JAVA_HOME

java -version
```

- 

## How to build

- Clone this project
```
git clone https://github.com/dinhdangkhoa0201/amazon-cloadwatch-for-aws-xray.git
cd amazon-cloadwatch-for-aws-xray
```

- Open class [CredentialsConstant.java](src/main/java/com/vntechies/awscloudwatchagentforawsxray/constants/CredentialsConstant.java)
- Change ACCESS_KEY and SECRET_ACCESS_KEY
```
public static final String ACCESS_KEY  = "";
public static final String SECRET_ACCESS_KEY = "";
```

- Install dependencies
```
mvn install
```

## Before deploy

- Create a DynamoDB table with name `Song_Table`
  - Primary Key: `id`
  - Sort Key: `name`
- Create a bucket with name `aws-cloudwatch-agent-for-aws-xray`

## API

- healthcheck
```
{{host}}/healthcheck
```

- Insert a DynamoDB record
```
{{host}}/api/dynamodb/addItem
```

- Put an object to S3
```
{{host}}/api/s3/putObject
```
