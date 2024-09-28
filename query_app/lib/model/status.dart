class Status {
  Status(
      {required this.code,
      required this.message,
      required this.responseTime,
      required this.resultCount});

  factory Status.fromJson(json) {
    return Status(
        code: json['code'] as int,
        message: json['message'] as String,
        responseTime: json['responseTime'] as int,
        resultCount: json['resultCount'] as int);
  }
  int code;
  String message;
  int responseTime;
  int resultCount;

  @override
  String toString() {
    return 'Trans{field1: $code, field2: $message,field3: $responseTime,field4: $resultCount}';
  }
}
