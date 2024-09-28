class DcreSizes {
  DcreSizes({required this.id, required this.name});

  factory DcreSizes.fromJson(json) {
    return DcreSizes(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
