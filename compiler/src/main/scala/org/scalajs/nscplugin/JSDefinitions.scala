/*
 * Scala.js (https://www.scala-js.org/)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package org.scalajs.nscplugin

import scala.tools.nsc._

/** Core definitions for Scala.js
 *
 *  @author Sébastien Doeraene
 */
trait JSDefinitions { self: JSGlobalAddons =>
  import global._

  // scalastyle:off line.size.limit

  object jsDefinitions extends JSDefinitionsClass

  import definitions._
  import rootMirror._

  class JSDefinitionsClass {

    lazy val HackedStringClass = getClassIfDefined("java.lang._String")
    lazy val HackedStringModClass = getModuleIfDefined("java.lang._String").moduleClass

    lazy val ScalaJSJSPackage = getPackage(newTermNameCached("scala.scalajs.js")) // compat 2.11
      lazy val JSPackage_typeOf        = getMemberMethod(ScalaJSJSPackage, newTermName("typeOf"))
      lazy val JSPackage_constructorOf = getMemberMethod(ScalaJSJSPackage, newTermName("constructorOf"))
      lazy val JSPackage_native        = getMemberMethod(ScalaJSJSPackage, newTermName("native"))
      lazy val JSPackage_undefined     = getMemberMethod(ScalaJSJSPackage, newTermName("undefined"))

    lazy val JSNativeAnnotation = getRequiredClass("scala.scalajs.js.native")

    lazy val JSAnyClass       = getRequiredClass("scala.scalajs.js.Any")
    lazy val JSDynamicClass   = getRequiredClass("scala.scalajs.js.Dynamic")
    lazy val JSObjectClass    = getRequiredClass("scala.scalajs.js.Object")
    lazy val JSThisFunctionClass = getRequiredClass("scala.scalajs.js.ThisFunction")

    lazy val UnionClass = getRequiredClass("scala.scalajs.js.$bar")

    lazy val JSArrayClass = getRequiredClass("scala.scalajs.js.Array")
      lazy val JSArray_apply  = getMemberMethod(JSArrayClass, newTermName("apply"))
      lazy val JSArray_update = getMemberMethod(JSArrayClass, newTermName("update"))

    lazy val JSFunctionClasses     = (0 to 22) map (n => getRequiredClass("scala.scalajs.js.Function"+n))
    lazy val JSThisFunctionClasses = (0 to 21) map (n => getRequiredClass("scala.scalajs.js.ThisFunction"+n))
    lazy val AllJSFunctionClasses  = JSFunctionClasses ++ JSThisFunctionClasses

    lazy val RuntimeExceptionClass    = requiredClass[RuntimeException]
    lazy val JavaScriptExceptionClass = getClassIfDefined("scala.scalajs.js.JavaScriptException")

    lazy val JSNameAnnotation          = getRequiredClass("scala.scalajs.js.annotation.JSName")
    lazy val JSBracketAccessAnnotation = getRequiredClass("scala.scalajs.js.annotation.JSBracketAccess")
    lazy val JSBracketCallAnnotation   = getRequiredClass("scala.scalajs.js.annotation.JSBracketCall")
    lazy val JSExportAnnotation        = getRequiredClass("scala.scalajs.js.annotation.JSExport")
    lazy val JSExportAllAnnotation     = getRequiredClass("scala.scalajs.js.annotation.JSExportAll")
    lazy val JSExportStaticAnnotation  = getRequiredClass("scala.scalajs.js.annotation.JSExportStatic")
    lazy val JSExportTopLevelAnnotation = getRequiredClass("scala.scalajs.js.annotation.JSExportTopLevel")
    lazy val JSImportAnnotation        = getRequiredClass("scala.scalajs.js.annotation.JSImport")
    lazy val JSGlobalAnnotation        = getRequiredClass("scala.scalajs.js.annotation.JSGlobal")
    lazy val JSGlobalScopeAnnotation   = getRequiredClass("scala.scalajs.js.annotation.JSGlobalScope")

    lazy val JavaDefaultMethodAnnotation = getRequiredClass("scala.scalajs.js.annotation.JavaDefaultMethod")

    lazy val JSImportNamespaceObject = getRequiredModule("scala.scalajs.js.annotation.JSImport.Namespace")

    lazy val ExposedJSMemberAnnot = getRequiredClass("scala.scalajs.js.annotation.internal.ExposedJSMember")
    lazy val JSOptionalAnnotation = getRequiredClass("scala.scalajs.js.annotation.internal.JSOptional")
    lazy val RawJSTypeAnnot = getRequiredClass("scala.scalajs.js.annotation.internal.RawJSType")
    lazy val WasPublicBeforeTyperClass = getRequiredClass("scala.scalajs.js.annotation.internal.WasPublicBeforeTyper")

    lazy val JSAnyTpe    = JSAnyClass.toTypeConstructor
    lazy val JSObjectTpe = JSObjectClass.toTypeConstructor

    lazy val JSFunctionTpes = JSFunctionClasses.map(_.toTypeConstructor)

    lazy val JSDynamicModule = JSDynamicClass.companionModule
      lazy val JSDynamic_newInstance = getMemberMethod(JSDynamicModule, newTermName("newInstance"))
    lazy val JSDynamicLiteral = getMemberModule(JSDynamicModule, newTermName("literal"))
      lazy val JSDynamicLiteral_applyDynamicNamed = getMemberMethod(JSDynamicLiteral, newTermName("applyDynamicNamed"))
      lazy val JSDynamicLiteral_applyDynamic = getMemberMethod(JSDynamicLiteral, newTermName("applyDynamic"))

    lazy val JSArrayModule = JSArrayClass.companionModule
      lazy val JSArray_create = getMemberMethod(JSArrayModule, newTermName("apply"))

    lazy val JSConstructorTagModule = getRequiredModule("scala.scalajs.js.ConstructorTag")
      lazy val JSConstructorTag_materialize = getMemberMethod(JSConstructorTagModule, newTermName("materialize"))

    lazy val SpecialPackageModule = getPackageObject("scala.scalajs.js.special")
      lazy val Special_in = getMemberMethod(SpecialPackageModule, newTermName("in"))
      lazy val Special_instanceof = getMemberMethod(SpecialPackageModule, newTermName("instanceof"))
      lazy val Special_delete = getMemberMethod(SpecialPackageModule, newTermName("delete"))
      lazy val Special_forin = getMemberMethod(SpecialPackageModule, newTermName("forin"))
      lazy val Special_debugger = getMemberMethod(SpecialPackageModule, newTermName("debugger"))

    lazy val BooleanReflectiveCallClass = getRequiredClass("scala.scalajs.runtime.BooleanReflectiveCall")
    lazy val CharacterReflectiveCallClass = getRequiredClass("scala.scalajs.runtime.CharacterReflectiveCall")
    lazy val NumberReflectiveCallClass = getRequiredClass("scala.scalajs.runtime.NumberReflectiveCall")
    lazy val IntegerReflectiveCallClass = getRequiredClass("scala.scalajs.runtime.IntegerReflectiveCall")
    lazy val LongReflectiveCallClass = getRequiredClass("scala.scalajs.runtime.LongReflectiveCall")

    lazy val RuntimePackageModule = getPackageObject("scala.scalajs.runtime")
      lazy val Runtime_wrapJavaScriptException    = getMemberMethod(RuntimePackageModule, newTermName("wrapJavaScriptException"))
      lazy val Runtime_unwrapJavaScriptException  = getMemberMethod(RuntimePackageModule, newTermName("unwrapJavaScriptException"))
      lazy val Runtime_toScalaVarArgs             = getMemberMethod(RuntimePackageModule, newTermName("toScalaVarArgs"))
      lazy val Runtime_toJSVarArgs                = getMemberMethod(RuntimePackageModule, newTermName("toJSVarArgs"))
      lazy val Runtime_constructorOf              = getMemberMethod(RuntimePackageModule, newTermName("constructorOf"))
      lazy val Runtime_newConstructorTag          = getMemberMethod(RuntimePackageModule, newTermName("newConstructorTag"))
      lazy val Runtime_createInnerJSClass         = getMemberMethod(RuntimePackageModule, newTermName("createInnerJSClass"))
      lazy val Runtime_createLocalJSClass         = getMemberMethod(RuntimePackageModule, newTermName("createLocalJSClass"))
      lazy val Runtime_withContextualJSClassValue = getMemberMethod(RuntimePackageModule, newTermName("withContextualJSClassValue"))
      lazy val Runtime_linkingInfo                = getMemberMethod(RuntimePackageModule, newTermName("linkingInfo"))

    lazy val Tuple2_apply = getMemberMethod(TupleClass(2).companionModule, nme.apply)

    // This is a def, since similar symbols (arrayUpdateMethod, etc.) are in runDefinitions
    // (rather than definitions) and we weren't sure if it is safe to make this a lazy val
    def ScalaRunTime_isArray: Symbol = getMemberMethod(ScalaRunTimeModule, newTermName("isArray")).suchThat(_.tpe.params.size == 2)

    lazy val BoxesRunTime_boxToCharacter = getMemberMethod(BoxesRunTimeModule, newTermName("boxToCharacter"))
    lazy val BoxesRunTime_unboxToChar    = getMemberMethod(BoxesRunTimeModule, newTermName("unboxToChar"))

    lazy val ReflectModule = getRequiredModule("scala.scalajs.reflect.Reflect")
      lazy val Reflect_registerLoadableModuleClass = getMemberMethod(ReflectModule, newTermName("registerLoadableModuleClass"))
      lazy val Reflect_registerInstantiatableClass = getMemberMethod(ReflectModule, newTermName("registerInstantiatableClass"))

    lazy val EnableReflectiveInstantiationAnnotation = getRequiredClass("scala.scalajs.reflect.annotation.EnableReflectiveInstantiation")

  }

  // scalastyle:on line.size.limit
}
