drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR2(255) PRIMARY KEY,
  resource_ids VARCHAR2(255),
  client_secret VARCHAR2(255),
  scope VARCHAR2(255),
  authorized_grant_types VARCHAR2(255),
  web_server_redirect_uri VARCHAR2(255),
  authorities VARCHAR2(255),
  access_token_validity NUMBER,
  refresh_token_validity NUMBER,
  additional_information VARCHAR2(4096),
  autoapprove VARCHAR2(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR2(255),
  token NUMBER VARBINARY,
  authentication_id VARCHAR2(255) PRIMARY KEY,
  user_name VARCHAR2(255),
  client_id VARCHAR2(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR2(255),
  token NUMBER VARBINARY,
  authentication_id VARCHAR2(255) PRIMARY KEY,
  user_name VARCHAR2(255),
  client_id VARCHAR2(255),
  authentication NUMBER VARBINARY,
  refresh_token VARCHAR2(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR2(255),
  token NUMBER VARBINARY,
  authentication NUMBER VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR2(255), authentication NUMBER VARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR2(255),
	clientId VARCHAR2(255),
	scope VARCHAR2(255),
	status VARCHAR2(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails (
  appId VARCHAR2(255) PRIMARY KEY,
  resourceIds VARCHAR2(255),
  appSecret VARCHAR2(255),
  scope VARCHAR2(255),
  grantTypes VARCHAR2(255),
  redirectUrl VARCHAR2(255),
  authorities VARCHAR2(255),
  access_token_validity NUMBER,
  refresh_token_validity NUMBER,
  additionalInformation VARCHAR2(4096),
  autoApproveScopes VARCHAR2(255)
);