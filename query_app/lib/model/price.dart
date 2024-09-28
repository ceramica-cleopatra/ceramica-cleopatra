class Price {
  String price;
  String priceType;

  Price({required this.price, required this.priceType});

  factory Price.fromJson(json) {
    return Price(
        price: json['price'] as String, priceType: json['priceType'] as String);
  }

  @override
  String toString() {
    return '{"price": "$price", "priceType": "$priceType"}';
  }
}
