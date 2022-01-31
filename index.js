import React from "react";
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,
  Platform,
  Button,
} from "react-native";
import { TextInput } from "react-native-gesture-handler";

const HelloWorld = () => {
  const [number, onChangeNumber] = React.useState(null);
  const androidBasicModule = NativeModules.RNBasicModule;

  const onButtonPress = () => {
    androidBasicModule.sendValueToNative(number);
    androidBasicModule.finishActivity();
  };

  return (
    <View style={styles.container}>
      <Text style={styles.hello}>Hello from RN component</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeNumber}
        value={number}
        placeholder="Provide a value to send it to native android"
        keyboardType="numeric"
      />
      <Button
        style={styles.button}
        title="Send value to native android"
        onPress={onButtonPress}
      />
    </View>
  );
};
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    marginHorizontal: 16,
  },
  hello: {
    fontSize: 20,
    textAlign: "center",
    margin: 10,
  },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
  },
  button: {
  },
});

AppRegistry.registerComponent("DemoRNApp", () => HelloWorld);
