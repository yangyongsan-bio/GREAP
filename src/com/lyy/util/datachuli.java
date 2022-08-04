package com.lyy.util;

import java.util.List;

public class datachuli {

	 public static String toStringToTremValues(int index, String toString) {
        String[] split = toString.split("\\[")[1].split("\\]")[0].split("}, ");
        String values = "";
        for (int i = index; i < split.length; i++) {
            String[] split1 = split[i].split("=");
            String value = split1[1];
            if (i != split.length - 1) {
                values +="\"" + value + "\"" + ",";
            } else {
                values += "\"" + value + "\"" ;
            }
        }
        return values.replaceAll("}","");
    }

	 public static void sortList(List<String> pValue, List<String> String1, List<String> String8) {
		 double minPvalue = 0D;
		 double middle = 0D;
		 int length = pValue.size();
         for (int i = 0; i < length; i++) {
         	minPvalue = Double.parseDouble(pValue.get(i));
         	for (int j = i; j < length; j++) {
         		middle = Double.parseDouble(pValue.get(j));
         		if (-Math.log10(minPvalue) > -Math.log10(middle)) {
         			String middle1 = String1.get(i);
         			String1.set(i, String1.get(j));
         			String1.set(j, middle1);

         			String middle4 = pValue.get(i);
         			pValue.set(i, pValue.get(j));
         			pValue.set(j, middle4);

         			String middle8 = String8.get(i);
         			String8.set(i, String8.get(j));
         			String8.set(j, middle8);
         			
         			minPvalue = minPvalue + middle;
         			middle = minPvalue - middle;
         			minPvalue = minPvalue - middle;
         		}
         	}
         }
	 }

}
