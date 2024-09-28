import 'dart:core';

import 'package:badges/badges.dart';
import 'package:data_table_2/data_table_2.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:provider/provider.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/service/crmk_service.dart';
import 'package:query_app/service/dcre_service.dart';
import 'package:query_app/service/price_service.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:toggle_switch/toggle_switch.dart';

import 'animation/nav-drawer.dart';
import 'cart_screen.dart';
import 'database/db_helper.dart';
import 'login_screen.dart';
import 'model/cart_item.dart';
import 'model/crmk_dlist.dart';
import 'model/crmk_govern.dart';
import 'model/crmk_plist.dart';
import 'model/price.dart';

class DcreSearchResultTable extends StatefulWidget {
  DcreSearchResultTable(
      {Key? key,
      required this.typeId,
      required this.sizeId,
      required this.factoryNo,
      required this.dekalaId,
      required this.frz,
      required this.colorId,
      required this.tablow,
      required this.govId})
      : super(key: key);
  @override
  String typeId;
  String sizeId;
  String dekalaId;
  String factoryNo;
  String frz;
  String colorId;
  String tablow;
  String govId;
  @override
  _DcreSearchResultState createState() => _DcreSearchResultState();
}

class _DcreSearchResultState extends State<DcreSearchResultTable> {
  _DcreSearchResultState();
  List<List<RowCell>> rows = [];
  List<ColumnHead> columns = [ColumnHead(columnName: '', freeQty: '')];
  String price = '0';
  String priceType = '';
  String productName = '';
  String packageSize = '';
  bool isLoadingFlag = false;
  Future<int>? val = null;
  DBHelper dbHelper = DBHelper();
  Object? selectedPList;
  Object? selectedDList;
  List<CrmkPlist> pList = [];
  List<CrmkDlist> dList = [];
  String dcrePlistNo = '';
  String dcreDlistNo = '';
  String pListId = '';
  String dListId = '';

  Future<void> validateLoginStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      widget.govId = prefs.getInt('govId').toString();
    });
    String loginTime = prefs.getString('loginTime') ?? "2023-01-01 12:00:00";
    int hours = DateTime.now().difference(DateTime.parse(loginTime)).inHours;
    if (widget.govId == "0" || hours >= 1) {
      MaterialPageRoute materialPageRoute =
          MaterialPageRoute(builder: (BuildContext context) => const Login());
      Navigator.push(context, materialPageRoute);
    }
  }

  @override
  void initState() {
    super.initState();
    validateLoginStatus();
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
    EasyLoading.init();
    val = _fillDcreItemDetails(
        widget.typeId,
        widget.sizeId,
        widget.factoryNo,
        widget.dekalaId,
        widget.frz,
        widget.govId,
        widget.colorId,
        widget.tablow);
  }

  List<Widget> createRadioPriceList(BuildContext context) {
    List<Widget> widgets = [];
    for (CrmkPlist list in pList) {
      widgets.add(
        RadioListTile(
          value: list,
          groupValue: selectedPList,
          title: Text(list.no.toString()),
          subtitle: Text(list.trnsDate),
          onChanged: (l) {
            setSelectedPListRadio(l!);
            Navigator.pop(context);
          },
          activeColor: Color.fromARGB(255, 165, 154, 119),
        ),
      );
    }
    return widgets;
  }

  setSelectedPListRadio(Object val) {
    setState(() {
      selectedPList = val;
      setPriceForCurrentPlist((val as CrmkPlist).id.toString());
      dcrePlistNo = (val as CrmkPlist).no.toString();
    });
  }

  Future<void> setPriceForCurrentPlist(String priceListId) async {
    Price priceModel = await PriceService.getDcrePrice(
        widget.typeId,
        widget.sizeId,
        widget.dekalaId,
        widget.factoryNo,
        widget.frz,
        widget.colorId,
        widget.tablow,
        priceListId,
        (selectedDList as CrmkDlist).id.toString());
    setState(() {
      this.price = priceModel.price;
      this.priceType = priceModel.priceType;
    });
  }

  List<Widget> createRadioDiscountList(BuildContext context) {
    List<Widget> widgets = [];
    for (CrmkDlist list in dList) {
      widgets.add(
        RadioListTile(
          value: list,
          groupValue: selectedDList,
          title: Text(list.no.toString()),
          subtitle: Text(list.trnsDate),
          onChanged: (l) {
            setSelectedDListRadio(l!);
            Navigator.pop(context);
          },
          activeColor: Color.fromARGB(255, 165, 154, 119),
        ),
      );
    }
    return widgets;
  }

  setSelectedDListRadio(Object val) {
    setState(() {
      selectedDList = val;
      setPriceForCurrentDlist((val as CrmkDlist).id.toString());
      dcreDlistNo = (val as CrmkDlist).no.toString();
    });
  }

  Future<void> setPriceForCurrentDlist(String discountListId) async {
    Price priceModel = await PriceService.getDcrePrice(
        widget.typeId,
        widget.sizeId,
        widget.dekalaId,
        widget.factoryNo,
        widget.frz,
        widget.colorId,
        widget.tablow,
        (selectedPList as CrmkPlist).id.toString(),
        discountListId);
    setState(() {
      this.price = priceModel.price;
      this.priceType = priceModel.priceType;
    });
  }

  Future<void> getPlList() async {
    pList = await DcreService.getDcrePlist();
    for (CrmkPlist list in pList) {
      if (list.id.toString() == pListId) {
        setState(() {
          selectedPList = list;
          dcrePlistNo = list.no.toString();
        });
      }
    }
  }

  Future<void> getDlList() async {
    dList = await DcreService.getDcreDlist();
    for (CrmkDlist list in dList) {
      if (list.id.toString() == dListId) {
        setState(() {
          selectedDList = list;
          dcreDlistNo = list.no.toString();
        });
      }
    }
  }

  Future<int> _fillDcreItemDetails(
      String typeId,
      String sizeId,
      String factoryNo,
      String dekalaId,
      String frz,
      String govId,
      String colorId,
      String tablow) async {
    columns = [];
    rows = [];
    EasyLoading.show(status: 'جارى البحث...');
    isLoadingFlag = true;
    List<CrmkGovern> governList = await DcreService.getDcreDetails(
        typeId, sizeId, dekalaId, factoryNo, frz, govId, colorId, tablow);
    EasyLoading.dismiss();
    isLoadingFlag = false;
    //   if (governList.isNotEmpty) {
    final CrmkGovern currentGovern = governList[0];
    price = currentGovern.price;
    packageSize = currentGovern.packageSize;
    productName = currentGovern.productName;
    priceType = currentGovern.priceType;
    pListId = currentGovern.pList;
    dListId = currentGovern.dList;
    getPlList();
    getDlList();
    columns.add(ColumnHead(columnName: 'سى', freeQty: ''));
    columns.add(ColumnHead(columnName: 'تون', freeQty: ''));
    columns.add(ColumnHead(
        columnName: 'الرصيد الفعلى',
        freeQty: currentGovern.totalReal.startsWith('-')
            ? "<${currentGovern.totalReal.replaceAll("-", "")}>"
            : currentGovern.totalReal));
    columns.add(ColumnHead(
        columnName: 'الرصيد الحر',
        freeQty: currentGovern.totalFree.startsWith('-')
            ? "<${currentGovern.totalFree.replaceAll("-", "")}"
            : currentGovern.totalFree));
    columns.add(ColumnHead(
        columnName: 'المحجوز',
        freeQty: currentGovern.totalRsrv.startsWith('-')
            ? "<${currentGovern.totalRsrv.replaceAll("-", "")}>"
            : currentGovern.totalRsrv));
    for (int j = 0; j < currentGovern.storeList.length; j++) {
      columns.add(ColumnHead(
          columnName: currentGovern.storeList[j].storeName,
          freeQty: currentGovern.storeList[j].totalFree));
    }
    for (int i = 0; i < currentGovern.distinctItems.length; i++) {
      List<RowCell> row = [];
      row.add(RowCell(
          cellValue: currentGovern.distinctItems[i].c,
          rowIndex: 0,
          color: Color.fromARGB(255, 165, 154, 119)));
      row.add(RowCell(
          cellValue: currentGovern.distinctItems[i].tone,
          rowIndex: 0,
          color: Color.fromARGB(255, 165, 154, 119)));
      row.add(RowCell(
          cellValue: currentGovern.distinctItems[i].realQty.startsWith('-')
              ? "<${currentGovern.distinctItems[i].realQty.replaceAll("-", "")}>"
              : currentGovern.distinctItems[i].realQty,
          rowIndex: i,
          color: Colors.white));
      row.add(RowCell(
          cellValue: currentGovern.distinctItems[i].freeQty.startsWith('-')
              ? "<${currentGovern.distinctItems[i].freeQty.replaceAll("-", "")}>"
              : currentGovern.distinctItems[i].freeQty,
          rowIndex: i,
          color: Colors.white));
      row.add(RowCell(
          cellValue: currentGovern.distinctItems[i].rsrvQty.startsWith('-')
              ? "<${currentGovern.distinctItems[i].rsrvQty.replaceAll("-", "")}>"
              : currentGovern.distinctItems[i].rsrvQty,
          rowIndex: i,
          color: Colors.white));
      for (int j = 0; j < currentGovern.storeList.length; j++) {
        row.add(RowCell(
            cellValue: currentGovern.storeList[j].itemDteailList[i].freeQty
                    .startsWith('-')
                ? "<${currentGovern.storeList[j].itemDteailList[i].freeQty.replaceAll("-", "")}>"
                : currentGovern.storeList[j].itemDteailList[i].freeQty,
            rowIndex: i,
            color: Colors.white));
      }
      rows.add(row);
    }
//    }
    return 1;
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<CartProvider>(context);
    void saveData(int itemId, double discount, double qty, double package) {
      dbHelper
          .insertCart(
        Cart(
            productId: itemId.toString(),
            productName: productName,
            discount: discount,
            initialPrice: double.parse(price),
            productPrice: ValueNotifier(((double.parse(price) -
                        (double.parse(price) * (discount / 100))) *
                    qty)
                .ceilToDouble()),
            quantity: ValueNotifier(qty),
            package: package,
            priceList: 1,
            discountList: 1,
            customerId: 1,
            crmkSehy: 'D'),
      )
          .then((value) {
        cart.addTotalPrice(
            ((double.parse(price) - (double.parse(price) * (discount / 100))) *
                qty));
        cart.addCounter();
        print('Product Added to cart');
      }).onError((error, stackTrace) {
        print(error.toString());
      });
    }

    return FutureBuilder(
        future: val,
        builder: (BuildContext ctx, AsyncSnapshot<Object?> snapshot) {
          EasyLoading.init();
          List<DataColumn> dataColumns = columns
              .map((ColumnHead column) => DataColumn(
                  label: Container(
                      padding: const EdgeInsets.all(8.0),
                      alignment: Alignment.center,
                      child: Column(children: [
                        Text(
                          column.columnName,
                          style: const TextStyle(
                              fontStyle: FontStyle.italic,
                              fontWeight: FontWeight.bold),
                        ),
                        Text(
                          column.freeQty,
                          style: const TextStyle(
                              fontStyle: FontStyle.italic,
                              fontWeight: FontWeight.bold),
                        )
                      ]))))
              .toList();
          try {
            int currentFrz = 0;
            if (widget.frz == '2') currentFrz = 1;
            final TextEditingController discountController =
                TextEditingController();
            final TextEditingController priceController =
                TextEditingController();
            final TextEditingController meterController =
                TextEditingController();
            final TextEditingController packageController =
                TextEditingController();
            final TextEditingController ordrMeterController =
                TextEditingController();
            packageController.text = '';
            priceController.text = '';
            ordrMeterController.text = '';
            discountController.addListener(() {
              if (discountController.text != '') {
                priceController.text =
                    '${(double.parse(price) - (double.parse(discountController.text) * double.parse(price)) / 100).toStringAsFixed(2)}';
              } else {
                priceController.text = '';
              }
            });

            meterController.addListener(() {
              if (meterController.text != '') {
                packageController.text =
                    '${(double.parse(meterController.text) / double.parse(packageSize)).ceil()}';
                ordrMeterController.text =
                    '${((double.parse(meterController.text) / double.parse(packageSize)).ceil() * double.parse(packageSize)).toStringAsFixed(2)}';
              } else {
                packageController.text = '';
                ordrMeterController.text = '';
              }
            });
            if (dataColumns.isNotEmpty) {
              return FlutterEasyLoading(
                  child: MaterialApp(
                      localizationsDelegates: const [
                    GlobalCupertinoLocalizations.delegate,
                    GlobalMaterialLocalizations.delegate,
                    GlobalWidgetsLocalizations.delegate,
                  ],
                      supportedLocales: const [
                    Locale('ar', 'AE')
                  ],
                      locale: const Locale('ar', 'AE'),
                      home: Scaffold(
                          drawer: NavDrawer(
                              'D',
                              widget.typeId,
                              widget.sizeId,
                              widget.factoryNo,
                              widget.dekalaId,
                              widget.frz,
                              widget.tablow,
                              widget.colorId),
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
                                  Text(
                                    price,
                                    style: const TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold),
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
                                        builder: (BuildContext context) =>
                                            SimpleDialog(
                                          children: [
                                            Row(children: [
                                              // Row(
                                              //     crossAxisAlignment:
                                              //         CrossAxisAlignment.center,
                                              //     textDirection:
                                              //         TextDirection.rtl,
                                              //     mainAxisAlignment:
                                              //         MainAxisAlignment.center,
                                              //     children: [
                                              //       ConstrainedBox(
                                              //           constraints:
                                              //               BoxConstraints
                                              //                   .tightFor(
                                              //                       width: 70,
                                              //                       height: 90),
                                              //           child:
                                              //               ElevatedButton.icon(
                                              //             style: ElevatedButton
                                              //                 .styleFrom(
                                              //                     primary: Color
                                              //                         .fromARGB(
                                              //                             141,
                                              //                             190,
                                              //                             153,
                                              //                             82)),
                                              //             onPressed: () {
                                              //               saveData(
                                              //                   12345,
                                              //                   double.parse(
                                              //                       discountController
                                              //                           .text),
                                              //                   double.parse(
                                              //                       ordrMeterController
                                              //                           .text),
                                              //                   double.parse(
                                              //                       packageController
                                              //                           .text));
                                              //               Navigator.pop(
                                              //                   context);
                                              //             },
                                              //             icon: Icon(
                                              //               Icons.shopping_cart,
                                              //               size: 30,
                                              //               color: Colors.black,
                                              //             ),
                                              //             label: Text(''),
                                              //           )),
                                              //       SizedBox(
                                              //         width: 10,
                                              //       ),
                                              //     ]),
                                              Column(children: [
                                                Row(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      SizedBox(
                                                          width: 90.0,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                priceController,
                                                          )),
                                                      const Text(':السعر'),
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
                                                            textAlign: TextAlign
                                                                .center,
                                                            keyboardType:
                                                                TextInputType
                                                                    .number,
                                                            controller:
                                                                discountController,
                                                          )),
                                                      const Text(':نسبة الخصم')
                                                    ]),
                                                Row(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                ordrMeterController,
                                                          )),
                                                      const Text(' :الأمتار'),
                                                      const SizedBox(
                                                        width: 5,
                                                      ),
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                packageController,
                                                          )),
                                                      const Text(' :الكراتين'),
                                                      const SizedBox(
                                                        width: 5,
                                                      ),
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            keyboardType:
                                                                TextInputType
                                                                    .number,
                                                            controller:
                                                                meterController,
                                                          )),
                                                      const Text(' :الأمتار'),
                                                    ])
                                              ]),
                                            ])
                                          ],
                                        ),
                                      )
                                    },
                                  ),
                                  Text(
                                    productName,
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
                                    activeFgColor:
                                        Color.fromARGB(255, 165, 154, 119),
                                    inactiveBgColor:
                                        Color.fromARGB(255, 165, 154, 119),
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

                                        Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                                builder: (BuildContext
                                                        context) =>
                                                    DcreSearchResultTable(
                                                        typeId: widget.typeId,
                                                        sizeId: widget.sizeId,
                                                        factoryNo:
                                                            widget.factoryNo,
                                                        dekalaId:
                                                            widget.dekalaId,
                                                        colorId: widget.colorId,
                                                        tablow: widget.tablow,
                                                        frz: frz,
                                                        govId: widget.govId)));
                                        print('$index');
                                      });
                                    },
                                  ),
                                  SizedBox(
                                    width: 5,
                                  ),
                                  ElevatedButton(
                                    child: Text(dcrePlistNo,
                                        style: TextStyle(color: Colors.black)),
                                    style: ElevatedButton.styleFrom(
                                      primary:
                                          Color.fromARGB(255, 165, 154, 119),
                                      elevation: 0,
                                    ),
                                    onPressed: () {
                                      showDialog<void>(
                                          context: context,
                                          builder: (context) => SimpleDialog(
                                              children: createRadioPriceList(
                                                  context)));
                                    },
                                  ),
                                  SizedBox(
                                    width: 3,
                                  ),
                                  ElevatedButton(
                                    child: Text(dcreDlistNo,
                                        style: TextStyle(
                                            color: Color.fromARGB(
                                                255, 165, 154, 119))),
                                    style: ElevatedButton.styleFrom(
                                      primary: Colors.black,
                                      elevation: 0,
                                    ),
                                    onPressed: () {
                                      showDialog<void>(
                                          context: context,
                                          builder: (context) => SimpleDialog(
                                              children: createRadioDiscountList(
                                                  context)));
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
                            //     position:
                            //         const BadgePosition(start: 30, bottom: 30),
                            //     child: IconButton(
                            //       onPressed: () {
                            //         Navigator.push(
                            //             context,
                            //             MaterialPageRoute(
                            //                 builder: (context) =>
                            //                     const CartScreen()));
                            //       },
                            //       icon: const Icon(Icons.shopping_cart),
                            //     ),
                            //   ),
                            //   const SizedBox(
                            //     width: 20.0,
                            //   ),
                            // ],
                          ),
                          body: SizedBox(
                            width: double.infinity,
                            child: DataTable2(
                              headingRowHeight: 60,
                              columnSpacing: 0,
                              dataRowHeight: 45,
                              horizontalMargin: 1,
                              dividerThickness: 1,
                              showBottomBorder: true,
                              headingTextStyle: const TextStyle(
                                fontWeight: FontWeight.w900,
                                color: Colors.black,
                              ),
                              headingRowColor: MaterialStateColor.resolveWith(
                                  (Set<MaterialState> states) =>
                                      Color.fromARGB(255, 165, 154, 119)),
                              columns: dataColumns,
                              rows: rows.map<DataRow>((List<RowCell> e) {
                                return DataRow(
                                    cells: e
                                        .map<DataCell>((RowCell e) =>
                                            DataCell(SizedBox(
                                              width: double.infinity,
                                              child: Container(
                                                height: double.infinity,
                                                width: double.infinity,
                                                color: e.rowIndex > 0 &&
                                                        e.rowIndex.isOdd
                                                    ? Color.fromARGB(
                                                        255, 248, 246, 230)
                                                    : e.color,
                                                child: Center(
                                                    child: Text(e.cellValue)),
                                              ),
                                            )))
                                        .toList());
                              }).toList(),
                            ),
                          ))));
            } else {
              int currentFrz = 0;
              if (widget.frz == '2') currentFrz = 1;
              final TextEditingController discountController =
                  TextEditingController();
              final TextEditingController priceController =
                  TextEditingController();
              final TextEditingController meterController =
                  TextEditingController();
              final TextEditingController packageController =
                  TextEditingController();
              final TextEditingController ordrMeterController =
                  TextEditingController();
              packageController.text = '';
              priceController.text = '';
              ordrMeterController.text = '';
              discountController.addListener(() {
                if (discountController.text != '') {
                  priceController.text =
                      '${(double.parse(price) - (double.parse(discountController.text) * double.parse(price)) / 100).toStringAsFixed(2)}';
                } else {
                  priceController.text = '';
                }
              });

              meterController.addListener(() {
                if (meterController.text != '') {
                  packageController.text =
                      '${(double.parse(meterController.text) / double.parse(packageSize)).ceil()}';
                  ordrMeterController.text =
                      '${((double.parse(meterController.text) / double.parse(packageSize)).ceil() * double.parse(packageSize)).toStringAsFixed(2)}';
                } else {
                  packageController.text = '';
                  ordrMeterController.text = '';
                }
              });

              if (isLoadingFlag == false) {
                return MaterialApp(
                    localizationsDelegates: const [
                      GlobalCupertinoLocalizations.delegate,
                      GlobalMaterialLocalizations.delegate,
                      GlobalWidgetsLocalizations.delegate,
                    ],
                    supportedLocales: const [
                      Locale('ar', 'AE')
                    ],
                    locale: const Locale('ar', 'AE'),
                    home: Scaffold(
                        drawer: NavDrawer(
                            'D',
                            widget.typeId,
                            widget.sizeId,
                            widget.factoryNo,
                            widget.dekalaId,
                            widget.frz,
                            widget.tablow,
                            widget.colorId),
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
                                Text(
                                  price,
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontWeight: FontWeight.bold),
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
                                      builder: (BuildContext context) =>
                                          SimpleDialog(
                                        children: [
                                          Row(children: [
                                            // Row(
                                            //     crossAxisAlignment:
                                            //         CrossAxisAlignment.center,
                                            //     textDirection:
                                            //         TextDirection.rtl,
                                            //     mainAxisAlignment:
                                            //         MainAxisAlignment.center,
                                            //     children: [
                                            //       ConstrainedBox(
                                            //           constraints:
                                            //               BoxConstraints
                                            //                   .tightFor(
                                            //                       width: 70,
                                            //                       height: 90),
                                            //           child:
                                            //               ElevatedButton.icon(
                                            //             style: ElevatedButton
                                            //                 .styleFrom(
                                            //                     primary: Color
                                            //                         .fromARGB(
                                            //                             141,
                                            //                             190,
                                            //                             153,
                                            //                             82)),
                                            //             onPressed: () {
                                            //               saveData(
                                            //                   12345,
                                            //                   double.parse(
                                            //                       discountController
                                            //                           .text),
                                            //                   double.parse(
                                            //                       ordrMeterController
                                            //                           .text),
                                            //                   double.parse(
                                            //                       packageController
                                            //                           .text));
                                            //               Navigator.pop(
                                            //                   context);
                                            //             },
                                            //             icon: Icon(
                                            //               Icons.shopping_cart,
                                            //               size: 30,
                                            //               color: Colors.black,
                                            //             ),
                                            //             label: Text(''),
                                            //           )),
                                            //       SizedBox(
                                            //         width: 10,
                                            //       ),
                                            //     ]),
                                            Column(children: [
                                              Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    SizedBox(
                                                        width: 90.0,
                                                        child: TextField(
                                                          textAlign:
                                                              TextAlign.center,
                                                          enabled: false,
                                                          controller:
                                                              priceController,
                                                        )),
                                                    const Text(':السعر'),
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
                                                          textAlign:
                                                              TextAlign.center,
                                                          keyboardType:
                                                              TextInputType
                                                                  .number,
                                                          controller:
                                                              discountController,
                                                        )),
                                                    const Text(':نسبة الخصم')
                                                  ]),
                                              Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    SizedBox(
                                                        width: 70.0,
                                                        height: 30,
                                                        child: TextField(
                                                          textAlign:
                                                              TextAlign.center,
                                                          enabled: false,
                                                          controller:
                                                              ordrMeterController,
                                                        )),
                                                    const Text(' :الأمتار'),
                                                    const SizedBox(
                                                      width: 5,
                                                    ),
                                                    SizedBox(
                                                        width: 70.0,
                                                        height: 30,
                                                        child: TextField(
                                                          textAlign:
                                                              TextAlign.center,
                                                          enabled: false,
                                                          controller:
                                                              packageController,
                                                        )),
                                                    const Text(' :الكراتين'),
                                                    const SizedBox(
                                                      width: 5,
                                                    ),
                                                    SizedBox(
                                                        width: 70.0,
                                                        height: 30,
                                                        child: TextField(
                                                          textAlign:
                                                              TextAlign.center,
                                                          keyboardType:
                                                              TextInputType
                                                                  .number,
                                                          controller:
                                                              meterController,
                                                        )),
                                                    const Text(' :الأمتار'),
                                                  ])
                                            ]),
                                          ])
                                        ],
                                      ),
                                    )
                                  },
                                ),
                                Text(
                                  productName,
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
                                  activeFgColor:
                                      Color.fromARGB(255, 165, 154, 119),
                                  inactiveBgColor:
                                      Color.fromARGB(255, 165, 154, 119),
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

                                      Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                              builder: (BuildContext context) =>
                                                  DcreSearchResultTable(
                                                      typeId: widget.typeId,
                                                      sizeId: widget.sizeId,
                                                      factoryNo:
                                                          widget.factoryNo,
                                                      dekalaId: widget.dekalaId,
                                                      colorId: widget.colorId,
                                                      tablow: widget.tablow,
                                                      frz: frz,
                                                      govId: widget.govId)));
                                      print('$index');
                                    });
                                  },
                                ),
                                SizedBox(
                                  width: 5,
                                ),
                                ElevatedButton(
                                  child: Text(dcrePlistNo,
                                      style: TextStyle(color: Colors.black)),
                                  style: ElevatedButton.styleFrom(
                                    primary: Color.fromARGB(255, 165, 154, 119),
                                    elevation: 0,
                                  ),
                                  onPressed: () {
                                    showDialog<void>(
                                        context: context,
                                        builder: (context) => SimpleDialog(
                                            children:
                                                createRadioPriceList(context)));
                                  },
                                ),
                                SizedBox(
                                  width: 3,
                                ),
                                ElevatedButton(
                                  child: Text(dcreDlistNo,
                                      style: TextStyle(
                                          color: Color.fromARGB(
                                              255, 165, 154, 119))),
                                  style: ElevatedButton.styleFrom(
                                    primary: Colors.black,
                                    elevation: 0,
                                  ),
                                  onPressed: () {
                                    showDialog<void>(
                                        context: context,
                                        builder: (context) => SimpleDialog(
                                            children: createRadioDiscountList(
                                                context)));
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
                          //     position:
                          //         const BadgePosition(start: 30, bottom: 30),
                          //     child: IconButton(
                          //       onPressed: () {
                          //         Navigator.push(
                          //             context,
                          //             MaterialPageRoute(
                          //                 builder: (context) =>
                          //                     const CartScreen()));
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
                        )));
              } else {
                return FlutterEasyLoading(
                  child: MaterialApp(
                      localizationsDelegates: const [
                        GlobalCupertinoLocalizations.delegate,
                        GlobalMaterialLocalizations.delegate,
                        GlobalWidgetsLocalizations.delegate,
                      ],
                      supportedLocales: const [
                        Locale('ar', 'AE')
                      ],
                      locale: const Locale('ar', 'AE'),
                      home: Scaffold(
                          drawer: NavDrawer(
                              'D',
                              widget.typeId,
                              widget.sizeId,
                              widget.factoryNo,
                              widget.dekalaId,
                              widget.frz,
                              widget.tablow,
                              widget.colorId),
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
                                  Text(
                                    price,
                                    style: const TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold),
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
                                        builder: (BuildContext context) =>
                                            SimpleDialog(
                                          children: [
                                            Row(children: [
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
                                              //                     primary: Color
                                              //                         .fromARGB(
                                              //                             141,
                                              //                             190,
                                              //                             153,
                                              //                             82)),
                                              //             onPressed: () {
                                              //               saveData(
                                              //                   12345,
                                              //                   double.parse(
                                              //                       discountController
                                              //                           .text),
                                              //                   double.parse(
                                              //                       ordrMeterController
                                              //                           .text),
                                              //                   double.parse(
                                              //                       packageController
                                              //                           .text));
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
                                              //         width: 10,
                                              //       ),
                                              //     ]),
                                              Column(children: [
                                                Row(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      SizedBox(
                                                          width: 90.0,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                priceController,
                                                          )),
                                                      const Text(':السعر'),
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
                                                            textAlign: TextAlign
                                                                .center,
                                                            keyboardType:
                                                                TextInputType
                                                                    .number,
                                                            controller:
                                                                discountController,
                                                          )),
                                                      const Text(':نسبة الخصم')
                                                    ]),
                                                Row(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                ordrMeterController,
                                                          )),
                                                      const Text(' :الأمتار'),
                                                      const SizedBox(
                                                        width: 5,
                                                      ),
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            enabled: false,
                                                            controller:
                                                                packageController,
                                                          )),
                                                      const Text(' :الكراتين'),
                                                      const SizedBox(
                                                        width: 5,
                                                      ),
                                                      SizedBox(
                                                          width: 70.0,
                                                          height: 30,
                                                          child: TextField(
                                                            textAlign: TextAlign
                                                                .center,
                                                            keyboardType:
                                                                TextInputType
                                                                    .number,
                                                            controller:
                                                                meterController,
                                                          )),
                                                      const Text(' :الأمتار'),
                                                    ])
                                              ]),
                                            ])
                                          ],
                                        ),
                                      )
                                    },
                                  ),
                                  Text(
                                    productName,
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
                                    activeFgColor:
                                        Color.fromARGB(255, 165, 154, 119),
                                    inactiveBgColor:
                                        Color.fromARGB(255, 165, 154, 119),
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

                                        Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                                builder: (BuildContext
                                                        context) =>
                                                    DcreSearchResultTable(
                                                        typeId: widget.typeId,
                                                        sizeId: widget.sizeId,
                                                        factoryNo:
                                                            widget.factoryNo,
                                                        dekalaId:
                                                            widget.dekalaId,
                                                        colorId: widget.colorId,
                                                        tablow: widget.tablow,
                                                        frz: frz,
                                                        govId: widget.govId)));
                                        print('$index');
                                      });
                                    },
                                  ),
                                  SizedBox(
                                    width: 5,
                                  ),
                                  ElevatedButton(
                                    child: Text(dcrePlistNo,
                                        style: TextStyle(color: Colors.black)),
                                    style: ElevatedButton.styleFrom(
                                      primary:
                                          Color.fromARGB(255, 165, 154, 119),
                                      elevation: 0,
                                    ),
                                    onPressed: () {
                                      showDialog<void>(
                                          context: context,
                                          builder: (context) => SimpleDialog(
                                              children: createRadioPriceList(
                                                  context)));
                                    },
                                  ),
                                  SizedBox(
                                    width: 3,
                                  ),
                                  ElevatedButton(
                                    child: Text(dcreDlistNo,
                                        style: TextStyle(
                                            color: Color.fromARGB(
                                                255, 165, 154, 119))),
                                    style: ElevatedButton.styleFrom(
                                      primary: Colors.black,
                                      elevation: 0,
                                    ),
                                    onPressed: () {
                                      showDialog<void>(
                                          context: context,
                                          builder: (context) => SimpleDialog(
                                              children: createRadioDiscountList(
                                                  context)));
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
                            //     position:
                            //         const BadgePosition(start: 30, bottom: 30),
                            //     child: IconButton(
                            //       onPressed: () {
                            //         Navigator.push(
                            //             context,
                            //             MaterialPageRoute(
                            //                 builder: (context) =>
                            //                     const CartScreen()));
                            //       },
                            //       icon: const Icon(Icons.shopping_cart),
                            //     ),
                            //   ),
                            //   const SizedBox(
                            //     width: 20.0,
                            //   ),
                            // ],
                          ))),
                );
              }
            }
          } catch (e) {
            print(e);
            int currentFrz = 0;
            if (widget.frz == '2') currentFrz = 1;
            final TextEditingController discountController =
                TextEditingController();
            final TextEditingController priceController =
                TextEditingController();
            final TextEditingController meterController =
                TextEditingController();
            final TextEditingController packageController =
                TextEditingController();
            final TextEditingController ordrMeterController =
                TextEditingController();
            packageController.text = ' :الكراتين';
            priceController.text = ' :السعر';
            ordrMeterController.text = ' :الأمتار';
            discountController.addListener(() {
              if (discountController.text != '') {
                priceController.text =
                    '${(double.parse(price) - (double.parse(discountController.text) * double.parse(price)) / 100).toStringAsFixed(2)} :السعر';
              } else {
                priceController.text = ':السعر';
              }
            });

            meterController.addListener(() {
              if (meterController.text != '') {
                packageController.text =
                    '${(double.parse(meterController.text) / double.parse(packageSize)).ceil()} :الكراتين';
                ordrMeterController.text =
                    '${((double.parse(meterController.text) / double.parse(packageSize)).ceil() * double.parse(packageSize)).toStringAsFixed(2)} :الأمتار';
              } else {
                packageController.text = ' :الكراتين';
                ordrMeterController.text = ' :الأمتار';
              }
            });

            return FlutterEasyLoading(
              child: MaterialApp(
                  localizationsDelegates: const [
                    GlobalCupertinoLocalizations.delegate,
                    GlobalMaterialLocalizations.delegate,
                    GlobalWidgetsLocalizations.delegate,
                  ],
                  supportedLocales: const [
                    Locale('ar', 'AE')
                  ],
                  locale: const Locale('ar', 'AE'),
                  home: Scaffold(
                      drawer: NavDrawer(
                          'D',
                          widget.typeId,
                          widget.sizeId,
                          widget.factoryNo,
                          widget.dekalaId,
                          widget.frz,
                          widget.tablow,
                          widget.colorId),
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
                              Text(
                                price,
                                style: const TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold),
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
                                    builder: (BuildContext context) =>
                                        SimpleDialog(
                                      children: [
                                        Row(children: [
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
                                          //                   width: 70, height: 90),
                                          //           child: ElevatedButton.icon(
                                          //             style:
                                          //                 ElevatedButton.styleFrom(
                                          //                     primary:
                                          //                         Color.fromARGB(
                                          //                             141,
                                          //                             190,
                                          //                             153,
                                          //                             82)),
                                          //             onPressed: () {
                                          //               saveData(
                                          //                   12345,
                                          //                   double.parse(
                                          //                       discountController
                                          //                           .text),
                                          //                   double.parse(
                                          //                       ordrMeterController
                                          //                           .text),
                                          //                   double.parse(
                                          //                       packageController
                                          //                           .text));
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
                                          //         width: 10,
                                          //       ),
                                          //     ]),
                                          Column(children: [
                                            Row(
                                                mainAxisAlignment:
                                                    MainAxisAlignment.center,
                                                children: [
                                                  SizedBox(
                                                      width: 90.0,
                                                      child: TextField(
                                                        textAlign:
                                                            TextAlign.center,
                                                        enabled: false,
                                                        controller:
                                                            priceController,
                                                      )),
                                                  const Text(':السعر'),
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
                                                        textAlign:
                                                            TextAlign.center,
                                                        keyboardType:
                                                            TextInputType
                                                                .number,
                                                        controller:
                                                            discountController,
                                                      )),
                                                  const Text(':نسبة الخصم')
                                                ]),
                                            Row(
                                                mainAxisAlignment:
                                                    MainAxisAlignment.center,
                                                children: [
                                                  SizedBox(
                                                      width: 70.0,
                                                      height: 30,
                                                      child: TextField(
                                                        textAlign:
                                                            TextAlign.center,
                                                        enabled: false,
                                                        controller:
                                                            ordrMeterController,
                                                      )),
                                                  const Text(' :الأمتار'),
                                                  const SizedBox(
                                                    width: 5,
                                                  ),
                                                  SizedBox(
                                                      width: 70.0,
                                                      height: 30,
                                                      child: TextField(
                                                        textAlign:
                                                            TextAlign.center,
                                                        enabled: false,
                                                        controller:
                                                            packageController,
                                                      )),
                                                  const Text(' :الكراتين'),
                                                  const SizedBox(
                                                    width: 5,
                                                  ),
                                                  SizedBox(
                                                      width: 70.0,
                                                      height: 30,
                                                      child: TextField(
                                                        textAlign:
                                                            TextAlign.center,
                                                        keyboardType:
                                                            TextInputType
                                                                .number,
                                                        controller:
                                                            meterController,
                                                      )),
                                                  const Text(' :الأمتار'),
                                                ])
                                          ]),
                                        ])
                                      ],
                                    ),
                                  )
                                },
                              ),
                              Text(
                                productName,
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
                                activeFgColor:
                                    Color.fromARGB(255, 165, 154, 119),
                                inactiveBgColor:
                                    Color.fromARGB(255, 165, 154, 119),
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

                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                DcreSearchResultTable(
                                                    typeId: widget.typeId,
                                                    sizeId: widget.sizeId,
                                                    factoryNo: widget.factoryNo,
                                                    dekalaId: widget.dekalaId,
                                                    colorId: widget.colorId,
                                                    tablow: widget.tablow,
                                                    frz: frz,
                                                    govId: widget.govId)));
                                  });
                                },
                              ),
                              SizedBox(
                                width: 5,
                              ),
                              ElevatedButton(
                                child: Text(dcrePlistNo,
                                    style: TextStyle(color: Colors.black)),
                                style: ElevatedButton.styleFrom(
                                  primary: Color.fromARGB(255, 165, 154, 119),
                                  elevation: 0,
                                ),
                                onPressed: () {
                                  showDialog<void>(
                                      context: context,
                                      builder: (context) => SimpleDialog(
                                          children:
                                              createRadioPriceList(context)));
                                },
                              ),
                              SizedBox(
                                width: 3,
                              ),
                              ElevatedButton(
                                child: Text(dcreDlistNo,
                                    style: TextStyle(
                                        color: Color.fromARGB(
                                            255, 165, 154, 119))),
                                style: ElevatedButton.styleFrom(
                                  primary: Colors.black,
                                  elevation: 0,
                                ),
                                onPressed: () {
                                  showDialog<void>(
                                      context: context,
                                      builder: (context) => SimpleDialog(
                                          children: createRadioDiscountList(
                                              context)));
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
                      ))),
            );
          }
        });
  }
}

class ColumnHead {
  ColumnHead({required this.columnName, required this.freeQty});
  String columnName;
  String freeQty;
}

class RowCell {
  RowCell(
      {required this.cellValue, required this.rowIndex, required this.color});
  String cellValue;
  int rowIndex;
  Color color;
}
