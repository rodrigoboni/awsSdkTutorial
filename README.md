# AWS SDK Tutorial

### Running this project

- Set aws credentials and region environment variables:
    - AWS_ACCESS_KEY_ID
    - AWS_SECRET_ACCESS_KEY
    - AWS_REGION

### Steps for using AWS SDK and getting access to AWS resources:

- Create or select user on IAM and get access key + secret key
- Config project dependencies:
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//setup-project-gradle.html
    - https:/docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//setup-project-maven.html
    - Add AWS SDK BOM
    - Add specific dependencies for each AWS service
- Create service client:
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//creating-clients.html
    - Use builder or defaultClient
- Working with credentials and region
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//setup-credentials.html
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//credentials.html
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//java-dg-region-selection.html
    - Can be set in aws config files or environment variables (most used options)
    - credentials:
        - ~/.aws/credentials
         ````
        [default]
        aws_access_key_id = your_access_key_id
        aws_secret_access_key = your_secret_access_key
        ````
        - var env
            ````
            export AWS_ACCESS_KEY_ID=your_access_key_id
            export AWS_SECRET_ACCESS_KEY=your_secret_access_key
            ````
    - region
        - ~/.aws./config
        ````
        [default]
        region = your_aws_region
        ````
        - var env
            ````
            export AWS_REGION=your_aws_region
            ````    
    - aws profile
        - set var env AWS_PROFILE to set AWS profile to be used to read credentials if needed (multiple profiles in credentials file)
    
    - aws credentials file location
        - default it's home/.aws
        - can be changed with environment variable AWS_CREDENTIAL_PROFILES_FILE
- AWS Sdk documentation
    - https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide//getting-started.html
