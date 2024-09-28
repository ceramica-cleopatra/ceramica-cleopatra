class CrmkCartItem {
  CrmkCartItem(
      {required this.itemId,
      required this.itemName,
      required this.initialPrice,
      required this.discount,
      required this.price,
      required this.meterQty,
      required this.carton});
  int itemId;
  String itemName;
  double initialPrice;
  double discount;
  double price;
  double meterQty;
  int carton;

  @override
  String toString() {
    return '{"itemId": "$itemId", "itemName": "$itemName"}';
  }
}
