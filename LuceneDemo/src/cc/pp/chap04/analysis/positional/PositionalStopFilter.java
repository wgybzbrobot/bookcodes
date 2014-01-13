package cc.pp.chap04.analysis.positional;

import java.io.IOException;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class PositionalStopFilter extends TokenFilter {

	private final CharArraySet stopWords;
	private final PositionIncrementAttribute posIncrAttr;
	private final TermAttribute termAttr;

	protected PositionalStopFilter(TokenStream in, CharArraySet stopWords) {
		super(in);
		this.stopWords = stopWords;
		posIncrAttr = addAttribute(PositionIncrementAttribute.class);
		termAttr = addAttribute(TermAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		int increment = 0;
		while (input.incrementToken()) {
			if (!stopWords.contains(termAttr.termBuffer(), 0, termAttr.termLength())) {  // 非停用词
				posIncrAttr.setPositionIncrement(posIncrAttr.getPositionIncrement() + increment);
				return true;
			}
			increment += posIncrAttr.getPositionIncrement();
		}

		return false;
	}

}
