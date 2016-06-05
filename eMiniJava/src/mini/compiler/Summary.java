package mini.compiler;

import java.util.ArrayList;

import mini.syntaxtree.NodeToken;

/**
 * The summary class is used to notify kinds of errors.
 * 
 * @author ddrmsdos
 * 
 */
public class Summary {
	private ArrayList<String> m_message = new ArrayList<String>();

	/**
	 * Nodes description
	 * @param summary
	 *            String
	 * @param token
	 *            NodeToken
	 */
	public void YieldDetail(String summary, NodeToken token) {
		YieldSummary(summary);
		YieldError(token);
	}

	/**
	 * General description
	 * @param summary
	 */
	public void YieldSummary(String summary) {
		m_message.add(summary + "\n");
	}

	/**
	 * Generate position description based on the nodes
	 * 
	 * @param token
	 * @return void
	 */
	public void YieldError(NodeToken token) {
		StringBuffer buf = new StringBuffer();
		buf.append("A Error occur at:" + token.tokenImage);
		if (token.beginLine != -1) {
			buf.append(" begin line is:" + token.beginLine);
		}
		if (token.endLine != -1) {
			buf.append(" end line is:" + token.endLine);
		}
		if (token.beginColumn != -1) {
			buf.append(" begin column is:" + token.beginColumn);
		}
		if (token.endColumn != -1) {
			buf.append(" end column is:" + token.endColumn
					+ " please check carefully.");
		}
		buf.append("\n");
		m_message.add(buf.toString());
	}

	/**
	 * check if the summary is existence.
	 * 
	 * @return boolean
	 */
	public boolean IsSummaryExist() {
		if (m_message.size() > 0)
			return true;
		return false;
	}

	/**
	 * return the summary as characters
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < m_message.size(); ++i) {
			buf.append(m_message.get(i));
		}
		return buf.toString();
	}
}