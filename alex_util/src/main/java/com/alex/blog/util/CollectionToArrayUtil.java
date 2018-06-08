package com.alex.blog.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author wamgchao
 * @version V1.0, 2017/10/26
 * @description 类描述 集合变数组
 */
public class CollectionToArrayUtil
{

    /**
     * @param
     * @return
     * @description 字符串集合变为二维数组
     * @author wangchao
     * @date 2017/10/26 10:43
     */
    public static String[][] stringCollectionToArray(Collection<String> collection, int arraySize)
    {
        if (CollectionUtils.isEmpty(collection))
        {
            return null;
        }
        int size = 1;
        int colSize = collection.size();
        if (arraySize < colSize)
        {
            size = colSize / arraySize + 1;
        }
        String[][] results = new String[size][];
        String[] srcObject = collection.toArray(new String[colSize]);
        int ilength = arraySize;
        for (int i = 0; i < size; i++)
        {
            int iStart = i * arraySize;
            if ((iStart + arraySize) > colSize)
            {
                ilength = colSize - iStart;
            }
            results[i] = new String[ilength];
            System.arraycopy(srcObject, iStart, results[i], 0, ilength);

        }
        return results;
    }


    public static <E> List<List<E>> collectionToTwodimen(Collection<E> colObj, int arraySize)
    {
        if (CollectionUtils.isEmpty(colObj))
        {
            return null;
        }
        ArrayList<List<E>> listResult = new ArrayList<>();
        int istart = 0;
        int nums = 0;
        ArrayList<E> tempList = new ArrayList<>();
        for (E src : colObj)
        {
            tempList.add(src);
            istart++;
            nums++;
            if (istart >= arraySize)
            {
                listResult.add(tempList);
                tempList = new ArrayList<>();
                istart = 0;
            }
            else
            {
                if (nums == colObj.size())
                {
                    listResult.add(tempList);
                }
            }

        }
        return listResult;
    }

    public static void main(String[] args)
    {
        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'C'; a++)
        {
            for (Integer b = 1; b <= 3; b++)
            {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }

        aTable.put("A", 1, "gwx");

        Set<Integer> keySet = aTable.columnKeySet();
        keySet.forEach(key -> System.out.println(key));
    }
}
