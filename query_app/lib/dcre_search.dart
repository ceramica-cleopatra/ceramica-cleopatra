import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_typeahead/flutter_typeahead.dart';
import 'package:provider/provider.dart';
import 'package:query_app/model/crmk_color.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/service/crmk_service.dart';
import 'package:query_app/service/dcre_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'cart_screen.dart';
import 'dcre_list.dart';
import 'login_screen.dart';
import 'model/crmk_dekalas.dart';
import 'model/crmk_sizes.dart';
import 'model/dcre_dekalas.dart';
import 'model/dcre_sizes.dart';
import 'model/dcre_types.dart';

class DcreSearch extends StatefulWidget {
  const DcreSearch({Key? key}) : super(key: key);
  @override
  _DcreSearchState createState() => _DcreSearchState();
}

class _DcreSearchState extends State<DcreSearch> {
  _DcreSearchState();

  int govId = 0;
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
    validateLoginStatus();
    _typeIdController.addListener(() async {
      if (_typeIdController.text != '') {
        try {
          List<DcreTypes> dcreTypesList =
              await DcreService.getDcreTypes(_typeIdController.text, '');
          if (dcreTypesList.isNotEmpty && dcreTypesList.length == 1) {
            _selectedTypeName = dcreTypesList[0].name;
            _typeController.text = dcreTypesList[0].name;
          } else if (dcreTypesList.isEmpty) {
            _selectedTypeName = '';
            _typeController.text = '';
          }
        } catch (e) {
          // _selectedTypeName = '';
          //  _typeController.text = '';
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
  final TextEditingController _colorController = TextEditingController();
  final TextEditingController _tablowController = TextEditingController();

  String _selectedTypeName = '';
  String _selectedTypeId = '';

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
              iconTheme: const IconThemeData(color: Colors.black),
              title: const Text(
                'ديكـــــور',
                style: TextStyle(
                  fontFamily: 'RPT Bold',
                  fontSize: 20.0,
                ),
              ),
              centerTitle: true,
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
                                return DcreService.getDcreTypes('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, DcreTypes suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (DcreTypes suggestion) {
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
                                return DcreService.getDcreSizes('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, DcreSizes suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (DcreSizes suggestion) {
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
                                return DcreService.getDcreDekalas('', pattern);
                              },
                              itemBuilder: (BuildContext context,
                                  DcreDekalas suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (DcreDekalas suggestion) {
                                _dekalaController.text = suggestion.name;
                              },
                            )),
                        Flexible(
                            flex: 2,
                            fit: FlexFit.loose,
                            child: TypeAheadFormField(
                              hideSuggestionsOnKeyboardHide: false,
                              textFieldConfiguration: TextFieldConfiguration(
                                keyboardType: TextInputType.visiblePassword,
                                autocorrect: false,
                                enableSuggestions: false,
                                decoration:
                                    const InputDecoration(labelText: 'اللون'),
                                controller: _colorController,
                              ),
                              suggestionsCallback: (String pattern) {
                                return CrmkService.getCrmkColors('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, CrmkColor suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (CrmkColor suggestion) {
                                _colorController.text = suggestion.name;
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
                                  const InputDecoration(labelText: 'تxن'),
                              controller: _tablowController,
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
                            child: const Text('بحث'),
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
                                          DcreList(
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
                                              colorId: '',
                                              colorName: _colorController.text,
                                              tablow: _tablowController.text,
                                              frz: _frzController.text));
                              await Navigator.push(context, materialPageRoute);
                            },
                          ),
                          SizedBox(width: 10, height: 1),
                          ElevatedButton(
                            child: const Text('بحث جديد'),
                            style: ElevatedButton.styleFrom(
                              fixedSize: Size(170, 30),
                              shape: const StadiumBorder(),
                              primary: Color.fromARGB(255, 165, 154, 119),
                              shadowColor: Color.fromARGB(0, 110, 109, 11),
                              padding: const EdgeInsets.symmetric(
                                  horizontal: 48, vertical: 6),
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
                              _colorController.text = '';
                              _tablowController.text = '';
                            },
                          ),
                        ])
                  ]),
                ),
              ),
            )));
  }
}
