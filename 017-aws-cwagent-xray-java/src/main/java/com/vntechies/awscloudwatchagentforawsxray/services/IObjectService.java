package com.vntechies.awscloudwatchagentforawsxray.services;

public interface IObjectService {
    Object putObject(String key, byte[] bytes);
}
