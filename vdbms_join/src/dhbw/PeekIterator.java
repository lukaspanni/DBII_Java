
package dhbw;

import java.util.Iterator;


public class PeekIterator<T>
{
	private Iterator<T> mUpstreamIterator;
	private T mCurrentValue;

	public PeekIterator(Iterator<T> it) {
		mUpstreamIterator = it;
		mCurrentValue = null;
	}
	
	public boolean hasValues() {
		return (mCurrentValue != null) || mUpstreamIterator.hasNext();
	}
	
	public T peek() {
		if ((mCurrentValue == null) && mUpstreamIterator.hasNext())
			mCurrentValue = mUpstreamIterator.next();
		return mCurrentValue;
	}
	
	public void next() {
		mCurrentValue = null;
	}
}
