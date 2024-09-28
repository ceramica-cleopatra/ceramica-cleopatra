import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:provider/provider.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/sehy_search_result_table.dart';
import 'package:query_app/service/sehy_service.dart';

import 'cart_screen.dart';
import 'crmk_search_result_table.dart';
import 'model/sehy_govern.dart';
import 'model/sehy_search_result.dart';

class SehyList extends StatefulWidget {
  SehyList(
      {Key? key,
      required this.typeId,
      required this.typeName,
      required this.nameId,
      required this.name,
      required this.dekalaId,
      required this.dekalaName,
      required this.colorId,
      required this.colorName,
      required this.frz})
      : super(key: key);
  String typeId;
  String typeName;
  String nameId;
  String name;
  String dekalaId;
  String dekalaName;
  String colorId;
  String colorName;
  String frz;
  @override
  SehyListState createState() => SehyListState(
      typeId: typeId,
      typeName: typeName,
      nameId: nameId,
      name: name,
      dekalaId: dekalaId,
      dekalaName: dekalaName,
      colorId: colorId,
      colorName: colorName,
      frz: frz);
}

class SehyListState extends State<SehyList> {
  SehyListState(
      {required this.typeId,
      required this.typeName,
      required this.nameId,
      required this.name,
      required this.dekalaId,
      required this.dekalaName,
      required this.colorId,
      required this.colorName,
      required this.frz});
  String typeId;
  String typeName;
  String nameId;
  String name;
  String dekalaId;
  String dekalaName;
  String colorId;
  String colorName;
  String frz;
  static const int _pageSize = 17;

  final PagingController<int, SehySearchResult> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((int pageKey) {
      _fetchPage(pageKey);
    });

    super.initState();
  }

  Future<void> _fetchPage(pageKey) async {
    try {
      final List<SehySearchResult> newItems =
          await SehyService.getSehySearchResult(
              typeId,
              typeName,
              nameId,
              name,
              dekalaId,
              dekalaName,
              colorId,
              colorName,
              frz,
              pageKey.toString(),
              _pageSize.toString());
      final bool isLastPage = newItems.length < _pageSize;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        final nextPageKey = pageKey + newItems.length;
        _pagingController.appendPage(newItems, nextPageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        color: Colors.white,
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
                  onPressed: () => Navigator.pop(context, false)),
              title: const Text(
                'صحــــــى',
                style: TextStyle(
                  fontFamily: 'RPT Bold',
                  fontSize: 20.0,
                ),
              ),
              centerTitle: true,
              iconTheme: const IconThemeData(color: Colors.black),
              backgroundColor: Color.fromARGB(141, 190, 153, 82),
              shadowColor: Colors.black,
              elevation: 50.0,
              foregroundColor: Colors.black,
              // actions: [
              //   Badge(
              //     badgeContent: Consumer<CartProvider>(
              //       builder: (context, value, child) {
              //         return Text(
              //           value.getCounter().toString(),
              //           style: const TextStyle(
              //               color: Colors.white, fontWeight: FontWeight.bold),
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
            body: Container(
                decoration: const BoxDecoration(
                    border: Border(bottom: BorderSide(width: 50))),
                padding: const EdgeInsets.all(10.0),
                child: CustomScrollView(
                  slivers: <Widget>[
                    PagedSliverList<int, SehySearchResult>(
                      pagingController: _pagingController,
                      builderDelegate:
                          PagedChildBuilderDelegate<SehySearchResult>(
                        itemBuilder: (BuildContext context,
                                SehySearchResult item, int index) =>
                            Card(
                                color: index % 2 == 0
                                    ? Color.fromARGB(255, 214, 210, 180)
                                    : Color.fromARGB(255, 255, 255, 255),
                                child: ListTile(
                                  title: Text(
                                      '${item.typeName} / ${item.name} / ${item.dekalaName} / ${item.colorName} / ${item.frz}',
                                      style: const TextStyle(
                                          color: Colors.black,
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold)),
                                  onTap: () async {
                                    SehyGovern sehyGov =
                                        await SehyService.getSehyTakmDetails(
                                            item.typeId,
                                            item.nameId,
                                            item.dekalaId,
                                            item.colorId,
                                            item.frz);
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                SehySearchResultTable(
                                                  takmName:
                                                      '${item.typeName} / ${item.name} / ${item.dekalaName} / ${item.colorName} / ${item.frz}',
                                                  typeId: item.typeId,
                                                  nameId: item.nameId,
                                                  dekalaId: item.dekalaId,
                                                  colorId: item.colorId,
                                                  frz: item.frz,
                                                )));
                                  },
                                )),
                      ),
                    )
                  ],
                ))));
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }
}
