
package dhbw;

import java.util.*;
import java.util.stream.Collectors;

public class Station {
    public DataTable mData = new DataTable();
    private int mValuesTransferred = 0;


    public void resetDataTable() {
        mData = new DataTable();
    }

    public void resetValuesTransferred() {
        mValuesTransferred = 0;
    }

    public int getValuesTransferred() {
        return mValuesTransferred;
    }


    // ************************************************************************
    // Merge-Join

    public void mergeJoin(DataTable result, Iterator<Data> jIt1, Iterator<Data> jIt2) {
    }

    // ************************************************************************
    // Merge-Join
    private void hashJoin(DataTable result, Iterator<Data> iter1, Iterator<Data> iter2) {
        Map<Integer, List<Data>> joinMap = new HashMap<>();
        while (iter1.hasNext()) {
            Data element = iter1.next();
            if (!joinMap.containsKey(element.C)) {
                joinMap.put(element.C, new ArrayList<>());
            }
            joinMap.get(element.C).add(element);
        }
        while (iter2.hasNext()) {
            Data element = iter2.next();
            if (!joinMap.containsKey(element.C)) continue;
            List<Data> list = joinMap.get(element.C);
            list.forEach(data -> result.addData(new DataResult(data, element)));
        }
    }


    private List<Data> getByKey(int key) {
        return mData.data().stream().filter(data -> data.C == key).collect(Collectors.toList());
    }

    // ************************************************************************
    // Join with Nested Loops

    // StationResult
    public void computeJoinNestedLoops(Station stR, Station stS) {
        for (Iterator<Data> it = stR.mData.getIterator(); it.hasNext(); ) {
            Data r = it.next();
            //Transfer r from stR to stResult
            mValuesTransferred += 3;
            List<Data> sData = stS.getByKey(r.C);
            //Transfer Key C from stResult to stS
            mValuesTransferred += 1;
            //Transfer sData from stS to stResult
            mValuesTransferred += sData.size() * 3;
            sData.forEach(s -> mData.addData(new DataResult(r, s)));
        }
    }


    // ************************************************************************
    // Join transferring two relations

    // StationResult
    public void computeJoinTransferTwo(Station stR, Station stS) {
        Iterator<Data> rIter = stR.mData.getIterator();
        Iterator<Data> sIter = stS.mData.getIterator();
        hashJoin(mData, rIter, sIter);
        mValuesTransferred += stR.mData.size() * 3 + stS.mData.size() * 3;
    }


    // ************************************************************************
    // Join transferring one relation

    // StationResult
    public void computeJoinTransferOne(Station stR, Station stS) {
        Iterator<Data> resultIt;
        if (stR.mData.size() > stS.mData.size()) {
            //Transfer stS -> stR
            resultIt = stR.getJoinTransferOne(stS);
        } else {
            //Transfer stR -> stS
            resultIt = stS.getJoinTransferOne(stR);
        }
        while (resultIt.hasNext()) {
            this.mData.addData(resultIt.next());
        }
        mValuesTransferred += 5 * this.mData.size();
    }


    //StationR/StationS
    private Iterator<Data> getJoinTransferOne(Station st) {
        DataTable resultTable = new DataTable();
        hashJoin(resultTable, st.mData.getIterator(), mData.getIterator());
        mValuesTransferred += 3 * st.mData.size();
        return resultTable.getIterator();
    }


    // ************************************************************************
    // Join filtering one relation

    // StationResult
    public void computeJoinFilterOne(Station stR, Station stS) {
    }


    // ************************************************************************
    // Join using hash

    // StationResult
    public void computeJoinHash(Station stR, Station stS) {
    }
}

