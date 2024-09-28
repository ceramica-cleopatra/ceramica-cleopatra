import 'dart:core';

import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:provider/provider.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/service/sehy_service.dart';
import 'package:toggle_switch/toggle_switch.dart';
import 'cart_screen.dart';
import 'database/db_helper.dart';
import 'model/cart_item.dart';
import 'model/sehy_govern.dart';

class SehySearchResultTable extends StatefulWidget {
  SehySearchResultTable(
      {Key? key,
      required this.takmName,
      required this.typeId,
      required this.nameId,
      required this.dekalaId,
      required this.colorId,
      required this.frz})
      : super(key: key);
  @override
  String takmName;
  @override
  String typeId;
  @override
  String nameId;
  @override
  String dekalaId;
  @override
  String colorId;
  @override
  String frz;

  @override
  _SehySearchResultState createState() => _SehySearchResultState();
}

class _SehySearchResultState extends State<SehySearchResultTable> {
  _SehySearchResultState();
  List<Widget> columns = [];
  double price = 0.0;
  List<bool> _selected = List<bool>.generate(100, (int index) => false);
  bool loading = false;
  SehyGovern sehyGovern =
      new SehyGovern(productName: "", storeList: [], distinctItems: []);
  DBHelper dbHelper = DBHelper();

  var cartMap = new Map<String, Cart>();

  final TextEditingController priceController = TextEditingController();
  @override
  void initState() {
    super.initState();
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
  }

  @override
  void dispose() {
    super.dispose();
  }

  Future<int> _fillSehyItemDetails(String typeId, String nameId,
      String dekalaId, String colorId, String frz) async {
    if (sehyGovern.distinctItems.isEmpty) {
      sehyGovern = await SehyService.getSehyTakmDetails(widget.typeId,
          widget.nameId, widget.dekalaId, widget.colorId, widget.frz);
      widget.takmName = sehyGovern.productName;
    }
    loading = false;

    return 1;
  }

  Widget build(BuildContext context) {
    final cart = Provider.of<CartProvider>(context);
    void saveData(double discount) {
      cartMap.forEach((k, v) => dbHelper
              .insertCart(
            Cart(
                productId: v.productId,
                productName: v.productName,
                discount: discount,
                initialPrice: v.initialPrice,
                productPrice: ValueNotifier(
                    ((v.initialPrice! - (v.initialPrice! * (discount / 100))))
                        .ceilToDouble()),
                quantity: ValueNotifier(1),
                package: 1,
                priceList: 1,
                discountList: 1,
                customerId: 1,
                crmkSehy: 'S'),
          )
              .then((value) {
            cart.addTotalPrice(((price - price * (discount / 100))));
            cart.addCounter();
            print('Product Added to cart');
          }).onError((error, stackTrace) {
            print(error.toString());
          }));
    }

    loading = true;
    setState(() {});
    final Future<int> val = _fillSehyItemDetails(widget.typeId, widget.nameId,
        widget.dekalaId, widget.colorId, widget.frz);
    return FutureBuilder(
        future: val,
        builder: (BuildContext ctx, AsyncSnapshot<Object?> snapshot) {
          int currentFrz = 0;

          if (widget.frz == '2') currentFrz = 1;
          final TextEditingController discountController =
              TextEditingController();
          final TextEditingController meterController = TextEditingController();

          priceController.text = ' :السعر';
          if (int.parse(widget.typeId) > 40 &&
              sehyGovern.distinctItems.isNotEmpty) {
            price = double.parse(sehyGovern.distinctItems[0].price);
          }
          discountController.addListener(() {
            if (discountController.text != '') {
              priceController.text =
                  '${(price - (double.parse(discountController.text) * price) / 100).toStringAsFixed(2)} :السعر';
            } else {
              priceController.text = ':السعر';
            }
          });

          if (sehyGovern.storeList.isEmpty && loading == false) {
            return MaterialApp(
              localizationsDelegates: const [
                GlobalCupertinoLocalizations.delegate,
                GlobalMaterialLocalizations.delegate,
                GlobalWidgetsLocalizations.delegate,
              ],
              supportedLocales: const [Locale('ar', 'AE')],
              locale: const Locale('ar', 'AE'),
              home: Scaffold(
                appBar: AppBar(
                  leading: IconButton(
                    icon: const Icon(Icons.arrow_back),
                    onPressed: () => Navigator.pop(context, false),
                  ),
                  backgroundColor: Color.fromARGB(141, 190, 153, 82),
                  iconTheme: const IconThemeData(color: Colors.black),
                  shadowColor: Colors.black,
                  elevation: 50.0,
                  title: SizedBox(
                    width: double.infinity,
                    child: Row(
                      children: [
                        const Text(
                          'السعر :',
                          style: const TextStyle(
                              color: Colors.black, fontWeight: FontWeight.bold),
                        ),
                        Text(
                          price.toString(),
                          style: const TextStyle(
                              color: Colors.black, fontWeight: FontWeight.bold),
                        ),
                        IconButton(
                          icon: const Icon(Icons.calculate),
                          color: Colors.black,
                          iconSize: 40,
                          onPressed: () => {
                            discountController.text = '',
                            priceController.text = ':السعر',
                            showDialog<String>(
                              context: context,
                              builder: (BuildContext context) => SimpleDialog(
                                children: [
                                  Row(
                                      mainAxisAlignment:
                                          MainAxisAlignment.center,
                                      children: [
                                        // Row(
                                        //     crossAxisAlignment:
                                        //         CrossAxisAlignment.center,
                                        //     textDirection: TextDirection.rtl,
                                        //     mainAxisAlignment:
                                        //         MainAxisAlignment.center,
                                        //     children: [
                                        //       ConstrainedBox(
                                        //           constraints:
                                        //               BoxConstraints.tightFor(
                                        //                   width: 70,
                                        //                   height: 90),
                                        //           child: ElevatedButton.icon(
                                        //             style: ElevatedButton
                                        //                 .styleFrom(
                                        //                     primary:
                                        //                         Color.fromARGB(
                                        //                             141,
                                        //                             190,
                                        //                             153,
                                        //                             82)),
                                        //             onPressed: () {
                                        //               saveData(double.parse(
                                        //                   discountController
                                        //                       .text));
                                        //               Navigator.pop(context);
                                        //             },
                                        //             icon: Icon(
                                        //               Icons.shopping_cart,
                                        //               size: 30,
                                        //               color: Colors.black,
                                        //             ),
                                        //             label: Text(''),
                                        //           )),
                                        //       SizedBox(
                                        //         width: 1,
                                        //       ),
                                        //     ]),
                                        SizedBox(
                                            width: 120.0,
                                            child: TextField(
                                              textAlign: TextAlign.right,
                                              enabled: false,
                                              controller: priceController,
                                            )),
                                        const SizedBox(
                                          width: 10,
                                        ),
                                        const Text('%'),
                                        const SizedBox(
                                          width: 5,
                                        ),
                                        SizedBox(
                                            width: 30.0,
                                            child: TextField(
                                              textAlign: TextAlign.center,
                                              keyboardType:
                                                  TextInputType.number,
                                              controller: discountController,
                                            )),
                                        const Text(':نسبة الخصم')
                                      ]),
                                ],
                              ),
                            )
                          },
                        ),
                        Text(
                          widget.takmName,
                          style: const TextStyle(
                              fontSize: 14, color: Colors.black),
                        ),
                        const SizedBox(
                          width: 5,
                        ),
                        ToggleSwitch(
                          minWidth: 65.0,
                          minHeight: 35.0,
                          cornerRadius: 20.0,
                          initialLabelIndex: currentFrz,
                          activeBgColor: const [Colors.black87],
                          activeFgColor: Color.fromARGB(255, 165, 154, 119),
                          inactiveBgColor: Color.fromARGB(255, 165, 154, 119),
                          inactiveFgColor: Colors.black,
                          totalSwitches: 2,
                          labels: const ['فرز أول', 'فرز ثانى'],
                          onToggle: (int? index) {
                            setState(() {
                              String frz = '';
                              if ('$index' == '0') {
                                frz = '1';
                              } else {
                                frz = '2';
                              }
                              String productName = widget.takmName.substring(
                                      0, widget.takmName.length - 1) +
                                  frz;
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (BuildContext context) =>
                                          SehySearchResultTable(
                                              typeId: widget.typeId,
                                              nameId: widget.nameId,
                                              dekalaId: widget.dekalaId,
                                              colorId: widget.colorId,
                                              frz: frz,
                                              takmName: productName)));
                            });
                          },
                        ),
                      ],
                    ),
                  ),
                  // actions: [
                  //   Badge(
                  //     badgeContent: Consumer<CartProvider>(
                  //       builder: (context, value, child) {
                  //         return Text(
                  //           value.getCounter().toString(),
                  //           style: const TextStyle(
                  //               color: Colors.white,
                  //               fontWeight: FontWeight.bold),
                  //         );
                  //       },
                  //     ),
                  //     position: const BadgePosition(start: 30, bottom: 30),
                  //     child: IconButton(
                  //       onPressed: () {
                  //         Navigator.push(
                  //             context,
                  //             MaterialPageRoute(
                  //                 builder: (context) => const CartScreen()));
                  //       },
                  //       icon: const Icon(Icons.shopping_cart),
                  //     ),
                  //   ),
                  //   const SizedBox(
                  //     width: 20.0,
                  //   ),
                  // ],
                ),
                body: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Center(
                      child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    mainAxisSize: MainAxisSize.max,
                    children: [
                      Image.asset('assets/images/oops.png'),
                      Text(
                        'عفوا ! لا يوجد رصيد لهذا الصنف',
                        textAlign: TextAlign.center,
                        style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: 21),
                      ),
                    ],
                  )),
                ),
              ),
            );
          } else if (sehyGovern.storeList.isNotEmpty && loading == false) {
            return MaterialApp(
              localizationsDelegates: const [
                GlobalCupertinoLocalizations.delegate,
                GlobalMaterialLocalizations.delegate,
                GlobalWidgetsLocalizations.delegate,
              ],
              supportedLocales: const [Locale('ar', 'AE')],
              locale: const Locale('ar', 'AE'),
              home: Scaffold(
                appBar: AppBar(
                  leading: IconButton(
                    icon: const Icon(Icons.arrow_back),
                    onPressed: () => Navigator.pop(context, false),
                  ),
                  backgroundColor: Color.fromARGB(141, 190, 153, 82),
                  iconTheme: const IconThemeData(color: Colors.black),
                  shadowColor: Colors.black,
                  elevation: 50.0,
                  title: SizedBox(
                    width: double.infinity,
                    child: Row(
                      children: [
                        const Text(
                          'السعر :',
                          style: const TextStyle(
                              color: Colors.black, fontWeight: FontWeight.bold),
                        ),
                        Text(
                          price.toString(),
                          style: const TextStyle(
                              color: Colors.black, fontWeight: FontWeight.bold),
                        ),
                        IconButton(
                          icon: const Icon(Icons.calculate),
                          color: Colors.black,
                          iconSize: 40,
                          onPressed: () => {
                            discountController.text = '',
                            priceController.text = ':السعر',
                            showDialog<String>(
                              context: context,
                              builder: (BuildContext context) => SimpleDialog(
                                children: [
                                  Row(
                                      mainAxisAlignment:
                                          MainAxisAlignment.center,
                                      children: [
                                        // Row(
                                        //     crossAxisAlignment:
                                        //         CrossAxisAlignment.center,
                                        //     textDirection: TextDirection.rtl,
                                        //     mainAxisAlignment:
                                        //         MainAxisAlignment.center,
                                        //     children: [
                                        //       ConstrainedBox(
                                        //           constraints:
                                        //               BoxConstraints.tightFor(
                                        //                   width: 70,
                                        //                   height: 90),
                                        //           child: ElevatedButton.icon(
                                        //             style: ElevatedButton
                                        //                 .styleFrom(
                                        //                     primary:
                                        //                         Color.fromARGB(
                                        //                             141,
                                        //                             190,
                                        //                             153,
                                        //                             82)),
                                        //             onPressed: () {
                                        //               saveData(double.parse(
                                        //                   discountController
                                        //                       .text));
                                        //               Navigator.pop(context);
                                        //             },
                                        //             icon: Icon(
                                        //               Icons.shopping_cart,
                                        //               size: 30,
                                        //               color: Colors.black,
                                        //             ),
                                        //             label: Text(''),
                                        //           )),
                                        //       SizedBox(
                                        //         width: 1,
                                        //       ),
                                        //     ]),
                                        SizedBox(
                                            width: 120.0,
                                            child: TextField(
                                              textAlign: TextAlign.right,
                                              enabled: false,
                                              controller: priceController,
                                            )),
                                        const SizedBox(
                                          width: 10,
                                        ),
                                        const Text('%'),
                                        const SizedBox(
                                          width: 5,
                                        ),
                                        SizedBox(
                                            width: 30.0,
                                            child: TextField(
                                              textAlign: TextAlign.center,
                                              keyboardType:
                                                  TextInputType.number,
                                              controller: discountController,
                                            )),
                                        const Text(':نسبة الخصم')
                                      ]),
                                ],
                              ),
                            )
                          },
                        ),
                        Text(
                          widget.takmName,
                          style: const TextStyle(
                              fontSize: 14, color: Colors.black),
                        ),
                        const SizedBox(
                          width: 5,
                        ),
                        ToggleSwitch(
                          minWidth: 65.0,
                          minHeight: 35.0,
                          cornerRadius: 20.0,
                          initialLabelIndex: currentFrz,
                          activeBgColor: const [Colors.black87],
                          activeFgColor: Color.fromARGB(255, 165, 154, 119),
                          inactiveBgColor: Color.fromARGB(255, 165, 154, 119),
                          inactiveFgColor: Colors.black,
                          totalSwitches: 2,
                          labels: const ['فرز أول', 'فرز ثانى'],
                          onToggle: (int? index) {
                            setState(() {
                              String frz = '';
                              if ('$index' == '0') {
                                frz = '1';
                              } else {
                                frz = '2';
                              }
                              String productName = widget.takmName.substring(
                                      0, widget.takmName.length - 1) +
                                  frz;
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (BuildContext context) =>
                                          SehySearchResultTable(
                                              typeId: widget.typeId,
                                              nameId: widget.nameId,
                                              dekalaId: widget.dekalaId,
                                              colorId: widget.colorId,
                                              frz: frz,
                                              takmName: productName)));
                            });
                          },
                        ),
                      ],
                    ),
                  ),
                  // actions: [
                  //   Badge(
                  //     badgeContent: Consumer<CartProvider>(
                  //       builder: (context, value, child) {
                  //         return Text(
                  //           value.getCounter().toString(),
                  //           style: const TextStyle(
                  //               color: Colors.white,
                  //               fontWeight: FontWeight.bold),
                  //         );
                  //       },
                  //     ),
                  //     position: const BadgePosition(start: 30, bottom: 30),
                  //     child: IconButton(
                  //       onPressed: () {
                  //         Navigator.push(
                  //             context,
                  //             MaterialPageRoute(
                  //                 builder: (context) => const CartScreen()));
                  //       },
                  //       icon: const Icon(Icons.shopping_cart),
                  //     ),
                  //   ),
                  //   const SizedBox(
                  //     width: 20.0,
                  //   ),
                  // ],
                ),
                body: SafeArea(
                  child: SingleChildScrollView(
                    child: Row(
                      children: [
                        fixedColumnWidget(),
                        ScrollableColumnWidget(sehyGovern),
                      ],
                    ),
                  ),
                ),
              ),
            );
          } else {
            return CircularProgressIndicator();
          }
        });
  }

  Widget fixedColumnWidget() {
    List<DataRow> dataRowList = [];

    for (int j = 0; j < sehyGovern.distinctItems.length; j++) {
      List<DataCell> dataCellList = [];
      dataCellList.add(DataCell(Container(
          child: Text(
        sehyGovern.distinctItems[j].itemName,
        style: TextStyle(fontWeight: FontWeight.bold),
      ))));
      dataCellList.add(DataCell(Container(
          alignment: AlignmentDirectional.center,
          child: Text(
            sehyGovern.distinctItems[j].price,
            style: TextStyle(fontWeight: FontWeight.bold),
          ))));
      dataCellList.add(DataCell(Container(
          alignment: AlignmentDirectional.center,
          child: Text(
            sehyGovern.distinctItems[j].realQty.startsWith('-')
                ? "<${sehyGovern.distinctItems[j].realQty.replaceAll("-", "")}>"
                : sehyGovern.distinctItems[j].realQty,
            style: TextStyle(fontWeight: FontWeight.bold),
          ))));
      dataCellList.add(DataCell(Container(
          alignment: AlignmentDirectional.center,
          child: Text(
            sehyGovern.distinctItems[j].freeQty.startsWith('-')
                ? "<${sehyGovern.distinctItems[j].freeQty.replaceAll("-", "")}>"
                : sehyGovern.distinctItems[j].freeQty,
            style: TextStyle(fontWeight: FontWeight.bold),
          ))));
      dataRowList.add(DataRow(
        color: j > 0 && j.isOdd
            ? MaterialStateColor.resolveWith(
                (states) => Color.fromARGB(255, 248, 246, 230))
            : MaterialStateColor.resolveWith((states) => Colors.white),
        cells: dataCellList,
        selected: _selected[j],
        onSelectChanged: (bool? selected) {
          setState(() {
            _selected[j] = selected!;
            if (selected) {
              price = price + double.parse(sehyGovern.distinctItems[j].price);
              cartMap[sehyGovern.distinctItems[j].itemName] = Cart(
                  productId: sehyGovern.distinctItems[j].itemId.toString(),
                  productName: sehyGovern.distinctItems[j].itemName,
                  discount: 0,
                  initialPrice: double.parse(sehyGovern.distinctItems[j].price),
                  productPrice: ValueNotifier(
                      double.parse(sehyGovern.distinctItems[j].price)
                          .ceilToDouble()),
                  quantity: ValueNotifier(1),
                  package: 1,
                  priceList: 1,
                  discountList: 1,
                  customerId: 1,
                  crmkSehy: 'S');
            } else {
              price = price - double.parse(sehyGovern.distinctItems[j].price);
              cartMap.remove(sehyGovern.distinctItems[j].itemName);
            }
            priceController.text = price.toString();
          });
        },
      ));
    }

    return DataTable(
      showCheckboxColumn: true,
      columnSpacing: 5,
      headingRowColor: MaterialStateColor.resolveWith(
          (Set<MaterialState> states) => Color.fromARGB(255, 165, 154, 119)),
      decoration: BoxDecoration(
        border: Border(
          right: BorderSide(
            width: 1,
          ),
        ),
      ),
      columns: [
        DataColumn(
            label: Text(
          'الصنف',
          style: TextStyle(fontWeight: FontWeight.bold),
        )),
        DataColumn(
            label: Text(
          'السعر',
          style: TextStyle(fontWeight: FontWeight.bold),
        )),
        DataColumn(
            label: Text(
          'الرصيد الفعلى',
          style: TextStyle(fontWeight: FontWeight.bold),
        )),
        DataColumn(
            label: Text(
          'الرصيد الحر',
          style: TextStyle(fontWeight: FontWeight.bold),
        )),
      ],
      rows: dataRowList,
    );
  }
}

class ScrollableColumnWidget extends StatelessWidget {
  ScrollableColumnWidget(SehyGovern sehyGovern) {
    this.sehyGovern = sehyGovern;
  }

  SehyGovern sehyGovern =
      new SehyGovern(productName: "", storeList: [], distinctItems: []);

  @override
  Widget build(BuildContext context) {
    List<DataColumn> dataColumnList = [];
    List<DataRow> dataRowList = [];

    for (int j = 0; j < sehyGovern.storeList.length; j++) {
      dataColumnList.add(DataColumn(
          label: Text(
        (sehyGovern.storeList[j].storeName),
        style: TextStyle(fontWeight: FontWeight.bold),
      )));
    }

    for (int j = 0; j < sehyGovern.distinctItems.length; j++) {
      List<DataCell> dataCellList = [];
      for (int i = 0; i < sehyGovern.storeList.length; i++) {
        dataCellList.add(DataCell(Container(
            alignment: AlignmentDirectional.center,
            child: Text(
              sehyGovern.storeList[i].itemDteailList[j].freeQty
                      .toString()
                      .startsWith('-')
                  ? "<${sehyGovern.storeList[i].itemDteailList[j].freeQty.toString().replaceAll("-", "")}>"
                  : sehyGovern.storeList[i].itemDteailList[j].freeQty
                      .toString(),
              style: TextStyle(fontWeight: FontWeight.bold),
            ))));
      }
      dataRowList.add(DataRow(
        cells: dataCellList,
        color: j > 0 && j.isOdd
            ? MaterialStateColor.resolveWith(
                (states) => Color.fromARGB(255, 248, 246, 230))
            : MaterialStateColor.resolveWith((states) => Colors.white),
      ));
    }
    try {
      return Expanded(
        child: SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: DataTable(
              headingRowColor: MaterialStateColor.resolveWith(
                  (Set<MaterialState> states) =>
                      Color.fromARGB(255, 165, 154, 119)),
              columnSpacing: 30,
              decoration: BoxDecoration(
                border: Border(
                  right: BorderSide(
                    width: 0.1,
                  ),
                ),
              ),
              columns: dataColumnList,
              rows: dataRowList),
        ),
      );
    } catch (e) {
      print(e);
      return const CircularProgressIndicator();
    }
  }
}
