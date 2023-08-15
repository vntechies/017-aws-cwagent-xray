# Amazon CloudWatch for AWS X-Ray

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
public static final String SECRET_ACCESS_KEY = "N6UgALtwldB1T/TYllup0ae4QBHbL5IcuC2BNXb5";
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
