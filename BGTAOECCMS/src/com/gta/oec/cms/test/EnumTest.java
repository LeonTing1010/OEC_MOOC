/**
 * EnumTest.java	  V1.0   2014-4-23 上午11:03:37
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.test;

import java.util.EnumMap;
import java.util.EnumSet;
/**
 * @description : 枚举类应用的demo
 * @author jianhua.huang[Bill Huang]
 * @since 2014-04-23 10:01:00
 * @version 1.0
 * */
public class EnumTest {
	
	public enum State{
		//1.未发布 2.已发布  3.审核中（待审核） 4.审核未通过 5.已结束
		UNPUBLISH(1,"未发布"),
		PUBLISHED(2,"已发布"),
		CHECKING(3,"审核中（待审核）"),
		UNPASS(4,"审核未通过"),
		FINISHED(5,"已结束");
		
		private Integer value;
		private String text;
		
		private State(Integer value, String text){
			this.value = value;
			this.text = text;
		}
		
		public static String getText(Integer value) {
			for (State cse : values()) {
				if (cse.value.equals(value)) {
					return cse.text;
				}
			}
			return null;
	    }  

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 四、   通常定义常量方法和枚举定义常量方法区别
		 *	以下内容可能有些无聊，但绝对值得一窥
		 *	1.    代码：
		 *		public class State {
		 *		public static final int ON = 1;
		 *		public static final Int OFF= 0;
		 *		}
		 *		有什么不好了，大家都这样用了很长时间了，没什么问题啊。
		 *		首先，它不是类型安全的。你必须确保是int
		 *		其次，你还要确保它的范围是0 和1
		 *		最后，很多时候你打印出来的时候，你只看到 1 和0 ，
		 *		但其没有看到代码的人并不知道你的企图，抛弃你所有旧的public static final 常量吧
		 *	2.    可以创建一个enum 类，把它看做一个普通的类。除了它不能继承其他类了。(java 是单继承，它已经继承了Enum),可以添加其他方法，覆盖它本身的方法
		 *	3.    switch() 参数可以使用enum 了
		 *	4.    values() 方法是编译器插入到enum 定义中的static 方法，所以，当你将enum 实例向上转型为父类Enum 是，values() 就不可访问了。解决办法：在Class 中有一个getEnumConstants() 方法，所以即便Enum 接口中没有values() 方法，我们仍然可以通过Class 对象取得所有的enum 实例
		 *	5.    无法从enum 继承子类，如果需要扩展enum 中的元素，在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组。达到将枚举元素进行分组。
		 *	6.    使用EnumSet 代替标志。enum 要求其成员都是唯一的，但是enum 中不能删除添加元素。
		 *	7.    EnumMap 的key 是enum ，value 是任何其他Object 对象。
		 *	8.    enum 允许程序员为eunm 实例编写方法。所以可以为每个enum 实例赋予各自不同的行为。
		 *	9.    使用enum 的职责链(Chain of Responsibility) . 这个关系到设计模式的职责链模式。以多种不同的方法来解决一个问题。然后将他们链接在一起。当一个请求到来时，遍历这个链，直到链中的某个解决方案能够处理该请求。
		 *	10.   使用enum 的状态机
		 *	11.   使用enum 多路分发
		 * */
		testTraversalEnum();
		testEnumMap();
		testEnumSet();
	}
	
	 /**
     * 演示枚举类型的遍历
     */
   private static void testTraversalEnum() {
	   State[] allState = State.values ();
      for (State aState : allState) {
          System. out .print( "  Key ： " + aState.name()+"; ");
          System. out .print( "  value ： " + aState.getValue()+"; ");
          System. out .print( "  text ： " + aState.getText()+"; ");
          System. out .print( "  text1 ： " + State.getText(aState.getValue())+"; ");
          System. out .println( " enum ： " + aState);
      }
      System. out .print( "  Key ： " + State.CHECKING.name()+"; ");
      System. out .print( "  value ： " + State.CHECKING.getValue()+"; ");
      System. out .print( "  text ： " + State.CHECKING.getText()+"; ");
      System. out .print( "  text1 ： " + State.getText(State.CHECKING.getValue())+"; ");
      System. out .println( " enum ： " + State.CHECKING);
   }

   /**
     * 演示 EnumMap 的使用， EnumMap 跟 HashMap 的使用差不多，只不过 key 要是枚举类型
     */
   private static void testEnumMap() {
      // 1. 演示定义 EnumMap 对象， EnumMap 对象的构造函数需要参数传入 , 默认是 key 的类的类型
      EnumMap<State, String> currEnumMap = new EnumMap<State, String>(State.class);
      currEnumMap.put(State.UNPUBLISH , "未发布" );
      currEnumMap.put(State.PUBLISHED , "已发布" );
      currEnumMap.put(State.CHECKING , "审核中（待审核）" );
      currEnumMap.put(State.UNPASS , "审核未通过" );
      currEnumMap.put(State.FINISHED , "已结束" );
      
      // 2. 遍历对象
      for (State aState : State.values ()) {
          System. out .println( "[key=" + aState.name() + ",value="
                 + currEnumMap.get(aState) + "]" );
      }
   }

   /**
     * 演示 EnumSet 如何使用， EnumSet 是一个抽象类，获取一个类型的枚举类型内容 <BR/>
     * 可以使用 allOf 方法
     */
   private static void testEnumSet() {
      EnumSet<State> currEnumSet = EnumSet.allOf (State.class);
      for (State aLightSetElement : currEnumSet) {
          System. out .println( " 当前 EnumSet 中数据为： " + aLightSetElement+" == "+aLightSetElement.getValue()+" == "+aLightSetElement.getText());
      }

   }
}