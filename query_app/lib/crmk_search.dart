import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_typeahead/flutter_typeahead.dart';
import 'package:provider/provider.dart';
import 'package:query_app/login_screen.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'cart_screen.dart';
import 'crmk_list.dart';
import 'database/db_helper.dart';
import 'model/crmk_dekalas.dart';
import 'model/crmk_sizes.dart';
import 'model/crmk_types.dart';
import 'service/crmk_service.dart';

class CrmkSearch extends StatefulWidget {
  const CrmkSearch({Key? key}) : super(key: key);
  @override
  _CrmkSearchState createState() => _CrmkSearchState();
}

class _CrmkSearchState extends State<CrmkSearch> {
  DBHelper dbHelper = DBHelper();
  _CrmkSearchState();

  Future<void> validateLoginStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      govId = prefs.getInt('govId') ?? 0;
    });
    String loginTime = prefs.getString('loginTime') ?? "2023-01-01 12:00:00";
    int hours = DateTime.now().difference(DateTime.parse(loginTime)).inHours;
    if (govId == 0 || hours >= 1) {
      MaterialPageRoute materialPageRoute =
          MaterialPageRoute(builder: (BuildContext context) => const Login());
      Navigator.push(context, materialPageRoute);
    }
  }

  @override
  void initState() {
    super.initState();
    EasyLoading.dismiss();
    validateLoginStatus();
    _typeController.addListener(() async {
      if (_typeController.text != '') {
        try {
          List<CrmkTypes> crmkTypesList =
              await CrmkService.getCrmkTypes('', _typeController.text);
          _selectedTypeId =
              (crmkTypesList.isNotEmpty && crmkTypesList.length == 1
                  ? crmkTypesList[0].id
                  : '');
          _typeIdController.text =
              crmkTypesList.isNotEmpty && crmkTypesList.length == 1
                  ? crmkTypesList[0].id
                  : '';
        } catch (e) {
          // _typeIdController.text = '';
          // _selectedTypeId = '';
        }
      } else {
        _selectedTypeId = '';
        _typeIdController.text = '';
      }
    });

    _typeIdController.addListener(() async {
      if (_typeIdController.text != '') {
        try {
          List<CrmkTypes> crmkTypesList =
              await CrmkService.getCrmkTypes(_typeIdController.text, '');
          _selectedTypeName =
              crmkTypesList.isNotEmpty && crmkTypesList.length == 1
                  ? crmkTypesList[0].name
                  : '';
          _typeController.text =
              crmkTypesList.isNotEmpty && crmkTypesList.length == 1
                  ? crmkTypesList[0].name
                  : '';
        } catch (e) {
          print(e.toString());
        }
      } else {
        _selectedTypeName = '';
        _typeController.text = '';
      }
    });
  }

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _typeController = TextEditingController();
  final TextEditingController _sizeController = TextEditingController();
  final TextEditingController _dekalaController = TextEditingController();
  final TextEditingController _typeIdController = TextEditingController();
  final TextEditingController _noController = TextEditingController();
  final TextEditingController _frzController = TextEditingController();

  String _selectedTypeName = '';
  String _selectedTypeId = '';
  int govId = 0;

  @override
  void dispose() {
    _typeIdController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
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
            appBar: AppBar(
              leading: IconButton(
                  icon: const Icon(Icons.arrow_back),
                  onPressed: () => Navigator.pop(context, false)),
              title: const Text(
                'سيراميـــك',
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
            body: Form(
              key: _formKey,
              child: SingleChildScrollView(
                physics: const NeverScrollableScrollPhysics(),
                child: Padding(
                  padding: const EdgeInsets.all(0.0),
                  child: Column(children: <Widget>[
                    Row(
                      children: [
                        Flexible(
                            flex: 1,
                            fit: FlexFit.loose,
                            child: TextField(
                              keyboardType: TextInputType.number,
                              decoration:
                                  const InputDecoration(labelText: 'الكود'),
                              controller: _typeIdController,
                            )),
                        Flexible(
                            flex: 4,
                            fit: FlexFit.loose,
                            child: TypeAheadFormField(
                              hideSuggestionsOnKeyboardHide: false,
                              textFieldConfiguration: TextFieldConfiguration(
                                keyboardType: TextInputType.visiblePassword,
                                autocorrect: false,
                                enableSuggestions: false,
                                decoration:
                                    const InputDecoration(labelText: 'النوع'),
                                controller: _typeController,
                              ),
                              suggestionsCallback: (String pattern) {
                                return CrmkService.getCrmkTypes('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, CrmkTypes suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (CrmkTypes suggestion) {
                                _typeController.text = suggestion.name;
                                _selectedTypeId = suggestion.id;
                              },
                              validator: (String? value) =>
                                  value!.isEmpty ? 'برجاء أختيار النوع' : '',
                              onSaved: (String? value) =>
                                  _selectedTypeName = value!,
                            )),
                        Flexible(
                            flex: 2,
                            fit: FlexFit.loose,
                            child: TextField(
                              keyboardType: TextInputType.number,
                              decoration:
                                  const InputDecoration(labelText: 'الرقم'),
                              controller: _noController,
                            )),
                        Flexible(
                            flex: 3,
                            fit: FlexFit.loose,
                            child: TypeAheadFormField(
                              hideSuggestionsOnKeyboardHide: false,
                              textFieldConfiguration: TextFieldConfiguration(
                                keyboardType: TextInputType.visiblePassword,
                                decoration: const InputDecoration(
                                  labelText: 'المقاس',
                                ),
                                controller: _sizeController,
                              ),
                              suggestionsCallback: (String pattern) {
                                return CrmkService.getCrmkSizes('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, CrmkSizes suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (CrmkSizes suggestion) {
                                _sizeController.text = suggestion.name;
                              },
                            )),
                        Flexible(
                            flex: 3,
                            fit: FlexFit.loose,
                            child: TypeAheadFormField(
                              hideSuggestionsOnKeyboardHide: false,
                              textFieldConfiguration: TextFieldConfiguration(
                                keyboardType: TextInputType.visiblePassword,
                                autocorrect: false,
                                enableSuggestions: false,
                                decoration: const InputDecoration(
                                    labelText: 'الديكالة'),
                                controller: _dekalaController,
                              ),
                              suggestionsCallback: (String pattern) {
                                return CrmkService.getCrmkDekalas('', pattern);
                              },
                              itemBuilder: (BuildContext context,
                                  CrmkDekalas suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (CrmkDekalas suggestion) {
                                _dekalaController.text = suggestion.name;
                              },
                            )),
                        Flexible(
                            flex: 1,
                            fit: FlexFit.loose,
                            child: TextField(
                              keyboardType: TextInputType.number,
                              autocorrect: false,
                              enableSuggestions: false,
                              decoration:
                                  const InputDecoration(labelText: 'الفرز'),
                              controller: _frzController,
                            )),
                      ],
                    ),
                    const SizedBox(
                      height: 10,
                      width: 1,
                    ),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          ElevatedButton(
                            child: const Text(
                              'بحـــــــث',
                              style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold),
                            ),
                            style: ElevatedButton.styleFrom(
                              fixedSize: Size(170, 30),
                              shape: const StadiumBorder(),
                              primary: Color.fromARGB(255, 165, 154, 119),
                              shadowColor: Color.fromARGB(0, 110, 109, 11),
                              padding: const EdgeInsets.symmetric(
                                  horizontal: 48, vertical: 6),
                            ),
                            onPressed: () async {
                              final MaterialPageRoute materialPageRoute =
                                  MaterialPageRoute(
                                      builder: (BuildContext context) =>
                                          CrmkList(
                                              typeId: _selectedTypeId == ''
                                                  ? ''
                                                  : _selectedTypeId,
                                              typeName: _selectedTypeName == ''
                                                  ? ''
                                                  : _selectedTypeName,
                                              sizeId: '',
                                              sizeName: _sizeController.text,
                                              dekalaId: '',
                                              dekalaName:
                                                  _dekalaController.text,
                                              factoryNo: _noController.text,
                                              frz: _frzController.text));
                              await Navigator.push(context, materialPageRoute);
                            },
                          ),
                          SizedBox(width: 10, height: 1),
                          ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              fixedSize: Size(170, 30),
                              shape: const StadiumBorder(),
                              primary: Color.fromARGB(255, 165, 154, 119),
                              shadowColor: Color.fromARGB(0, 110, 109, 11),
                              padding: const EdgeInsets.symmetric(
                                  horizontal: 48, vertical: 6),
                            ),
                            child: const Text(
                              'بحث جديد',
                              style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold),
                            ),
                            onPressed: () async {
                              _typeIdController.text = '';
                              _typeController.text = '';
                              _selectedTypeId = '';
                              _selectedTypeName = '';
                              _sizeController.text = '';
                              _dekalaController.text = '';
                              _noController.text = '';
                              _frzController.text = '';
                            },
                          ),
                        ])
                  ]),
                ),
              ),
            )));
  }
}
