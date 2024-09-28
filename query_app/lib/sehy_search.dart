import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_typeahead/flutter_typeahead.dart';
import 'package:provider/provider.dart';
import 'package:query_app/model/sehy_names.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/sehy_list.dart';
import 'package:query_app/service/sehy_service.dart';

import 'cart_screen.dart';
import 'crmk_list.dart';
import 'model/crmk_color.dart';
import 'model/sehy_dekalas.dart';
import 'model/sehy_types.dart';
import 'service/crmk_service.dart';

class SehySearch extends StatefulWidget {
  const SehySearch({Key? key}) : super(key: key);
  @override
  _SehySearchState createState() => _SehySearchState();
}

class _SehySearchState extends State<SehySearch> {
  _SehySearchState();
  @override
  void initState() {
    super.initState();
    // _typeController.addListener(() async {
    //   if (_typeController.text != '') {
    //     try {
    //       List<SehyTypes> sehyTypesList =
    //           await SehyService.getSehyTypes('', _typeController.text);
    //       _selectedTypeId =
    //           (sehyTypesList.isNotEmpty && sehyTypesList.length == 1
    //               ? sehyTypesList[0].id
    //               : '');
    //       _typeIdController.text =
    //           sehyTypesList.isNotEmpty && sehyTypesList.length == 1
    //               ? sehyTypesList[0].id
    //               : '';
    //     } catch (e) {
    //       // _typeIdController.text = '';
    //       // _selectedTypeId = '';
    //     }
    //   } else {
    //     _selectedTypeId = '';
    //     _typeIdController.text = '';
    //   }
    // });

    _typeIdController.addListener(() async {
      if (_typeIdController.text != '') {
        try {
          List<SehyTypes> sehyTypesList =
              await SehyService.getSehyTypes(_typeIdController.text, '');
          _selectedTypeName =
              sehyTypesList.isNotEmpty && sehyTypesList.length == 1
                  ? sehyTypesList[0].name
                  : '';
          _typeController.text =
              sehyTypesList.isNotEmpty && sehyTypesList.length == 1
                  ? sehyTypesList[0].name
                  : '';
          _selectedTypeId = _typeIdController.text;
        } catch (e) {
          print(e);
        }
      } else {
        _selectedTypeName = '';
        _selectedTypeId = '';
        _typeController.text = '';
      }
    });
  }

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _typeController = TextEditingController();
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _dekalaController = TextEditingController();
  final TextEditingController _typeIdController = TextEditingController();
  final TextEditingController _colorController = TextEditingController();
  final TextEditingController _frzController = TextEditingController();

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
                                return SehyService.getSehyTypes('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, SehyTypes suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (SehyTypes suggestion) {
                                _typeController.text = suggestion.name;
                                _selectedTypeId = suggestion.id;
                              },
                              validator: (String? value) =>
                                  value!.isEmpty ? 'برجاء أختيار النوع' : '',
                              onSaved: (String? value) =>
                                  _selectedTypeName = value!,
                            )),
                        Flexible(
                            flex: 3,
                            fit: FlexFit.loose,
                            child: TypeAheadFormField(
                              hideSuggestionsOnKeyboardHide: false,
                              textFieldConfiguration: TextFieldConfiguration(
                                keyboardType: TextInputType.visiblePassword,
                                decoration: const InputDecoration(
                                  labelText: 'الإسم',
                                ),
                                controller: _nameController,
                              ),
                              suggestionsCallback: (String pattern) {
                                return SehyService.getSehyNames('', pattern);
                              },
                              itemBuilder:
                                  (BuildContext context, SehyNames suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (SehyNames suggestion) {
                                _nameController.text = suggestion.name;
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
                                return SehyService.getSehyDekalas('', pattern);
                              },
                              itemBuilder: (BuildContext context,
                                  SehyDekalas suggestion) {
                                return ListTile(
                                  title: Text(suggestion.name),
                                );
                              },
                              transitionBuilder: (BuildContext context,
                                  Widget suggestionsBox,
                                  AnimationController? controller) {
                                return suggestionsBox;
                              },
                              onSuggestionSelected: (SehyDekalas suggestion) {
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
                              if (_typeIdController.text.isEmpty &&
                                  _typeController.text.isEmpty) {
                                showDialog(
                                  context: context,
                                  builder: (BuildContext context) {
                                    return AlertDialog(
                                      backgroundColor:
                                          Color.fromARGB(255, 165, 154, 119),
                                      content: Container(
                                          width: 200,
                                          child: Row(
                                              mainAxisSize: MainAxisSize.min,
                                              mainAxisAlignment:
                                                  MainAxisAlignment.center,
                                              crossAxisAlignment:
                                                  CrossAxisAlignment.center,
                                              children: <Widget>[
                                                Image.asset(
                                                    'assets/images/error.png'),
                                                Text(
                                                  'يجب تحديد النوع',
                                                  textAlign: TextAlign.center,
                                                  style: TextStyle(
                                                      color: Colors.black,
                                                      fontSize: 20,
                                                      fontWeight:
                                                          FontWeight.bold),
                                                ),
                                              ])),
                                      actions: [],
                                      insetPadding: EdgeInsets.zero,
                                      contentPadding: EdgeInsets.zero,
                                      clipBehavior: Clip.hardEdge,
                                    );
                                  },
                                );
                              } else {
                                final MaterialPageRoute materialPageRoute =
                                    MaterialPageRoute(
                                        builder:
                                            (BuildContext context) =>
                                                SehyList(
                                                    typeId:
                                                        _selectedTypeId == ''
                                                            ? ''
                                                            : _selectedTypeId,
                                                    typeName:
                                                        _selectedTypeName == ''
                                                            ? ''
                                                            : _selectedTypeName,
                                                    nameId: '',
                                                    name: _nameController.text,
                                                    dekalaId: '',
                                                    dekalaName:
                                                        _dekalaController.text,
                                                    colorId: '',
                                                    colorName:
                                                        _colorController.text,
                                                    frz: _frzController.text));
                                await Navigator.push(
                                    context, materialPageRoute);
                              }
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
                              _nameController.text = '';
                              _dekalaController.text = '';
                              _colorController.text = '';
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
