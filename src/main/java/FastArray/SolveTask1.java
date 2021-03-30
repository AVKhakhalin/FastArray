package FastArray;

public class SolveTask1
{
    int numberThreads;
    int sizeArray;
    float[] fastArray;

    SolveTask1(int _numberThreads, int _sizeArray)
    {
        this.numberThreads = _numberThreads;
        this.sizeArray = _sizeArray;

        fastArray = new float[sizeArray];
        for (int i = 0; i < fastArray.length; i++)
        {
            fastArray[i] = 1f;
        }
    }

    public long method1()
    {
        //region Расчёт времени выполнения метода 1
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < fastArray.length; i++)
        {
            fastArray[i] = (float)(fastArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long endTime = System.currentTimeMillis();
        //endregion

        return endTime - startTime;
    }

    public long method2()
    {
        long startTime = System.currentTimeMillis();

        SolveTask1.runThreads[] method2 = new SolveTask1.runThreads[numberThreads];
        for (int i = 0; i < numberThreads; i++)
        {
            method2[i] = new SolveTask1.runThreads("Method2_" + i, i, numberThreads);
//            System.out.println("Запущен процесс №" + (i + 1) + " с именем " + method2[i].getName());
            method2[i].start();
/*            try
            {
                method2[i].join();
            }
            catch (Exception e)
            {
            }
 */
        }
        //endregion

        for (int i = 0; i < numberThreads; i++)
        {
//            while (method2[i].isAlive() == true)
//            {
                try
                {
                    method2[i].join();
//                    Thread.sleep(1000);
                }
                catch (Exception e)
                {
                }
//            }
        }
//        summaryTimeToWork += method2[i].getTimeToWork();

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    class runThreads extends Thread
    {
        int numberCurThread = 0;
        int numberThreads;
        float[] fastArray_h;
        int numberArrayElements;
        int delta;

        runThreads (String name, int _numberCurThreads, int _numberThreads)
        {
            super(name);
            this.numberCurThread = _numberCurThreads;
            if (_numberThreads > 0)
            {
                this.numberThreads = _numberThreads;
            }
            else
            {
                this.numberThreads = 1;
            }
        }

        @Override
        public void run()
        {
            if (numberCurThread < numberThreads - 1)
            {
                numberArrayElements = Math.round(fastArray.length / numberThreads);
            }
            else
            {
                numberArrayElements = fastArray.length - Math.round(fastArray.length / numberThreads) * numberCurThread;
            }
//            System.out.println("numberCurThread = " + numberCurThread + ", numberThreads = " + numberThreads + ", numberArrayElements = " + numberArrayElements);

            fastArray_h = new float[numberArrayElements];
            if ((numberCurThread < numberThreads) && (fastArray_h.length > 0))
            {
                // Разбивка массива h
                if (numberCurThread < numberThreads - 1)
                {
                    System.arraycopy(fastArray, numberArrayElements * numberCurThread, fastArray_h, 0, numberArrayElements);
                }
                else
                {
                    System.arraycopy(fastArray, Math.round(fastArray.length / numberThreads) * numberCurThread, fastArray_h, 0, numberArrayElements);
                }
                // Расчёт значений массива h
                if (numberCurThread < numberThreads - 1)
                {
                    delta = numberArrayElements * numberCurThread;
                }
                else
                {
                    delta = Math.round(fastArray.length / numberThreads) * numberCurThread;
                }
                for (int i = 0; i < numberArrayElements; i++)
                {
//                    System.out.println(i + " " + (i + delta));
                    fastArray_h[i] = (float)(fastArray_h[i] * Math.sin(0.2f + (i + delta) / 5) * Math.cos(0.2f + (i + delta) / 5) * Math.cos(0.4f + (i + delta) / 2));
                }
                // Склейка массива h
                if (numberCurThread < numberThreads - 1)
                {
                    System.arraycopy(fastArray_h, 0, fastArray, numberArrayElements * numberCurThread, numberArrayElements);
                }
                else
                {
                    System.arraycopy(fastArray_h, 0, fastArray, Math.round(fastArray.length / numberThreads) * numberCurThread, numberArrayElements);
                }
            }
        }
    }
}
