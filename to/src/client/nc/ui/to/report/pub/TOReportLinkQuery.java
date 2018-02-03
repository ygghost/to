package nc.ui.to.report.pub;

import java.awt.Container;

import javax.swing.Action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.pub.smart.tracedata.ITraceDataOperator;
import nc.pub.smart.tracedata.TraceDataInterface;
import nc.pub.smart.tracedata.TraceDataParam;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.vo.ml.NCLangRes4VoTransl;

/**
 * �ڲ�����ģ�鱨������ ����idΪ cbillid ���ݺ�Ϊ vbillcode �������� vbilltypecode ����ʹ�ô���
 * 
 * @date 2018-01-29
 * @author yegz
 */
@SuppressWarnings("restriction")
public class TOReportLinkQuery implements ITraceDataOperator,
		TraceDataInterface {

	private static final String BILL_FINDER_CLASSNAME = "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

	@Override
	public Action[] ctreateExtensionActions() {
		return null;
	}

	@Override
	public com.ufida.report.free.userdef.IMenuActionInfo getMenuItemInfo() {
		com.ufida.report.free.userdef.DefaultMenu dmenu = new com.ufida.report.free.userdef.DefaultMenu(
				"linkBill", NCLangRes.getInstance().getStrByID("4006005_0",
						"04006005-0008")/* ���鵥�� */);
		return dmenu;
	}

	@Override
	public ITraceDataOperator[] provideTraceDataOperator() {
		return new TOReportLinkQuery[] { new TOReportLinkQuery() };
	}

	@Override
	public void traceData(Container container, TraceDataParam param) {
		if (container == null || param == null) {
			String hint = NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006005_0", "04006005-0000")/* @res "��ѡ���У�" */;
			NCLangRes nclang = NCLangRes.getInstance();
			MessageDialog.showHintDlg(container,
					nclang.getStrByID("4006005_0", "04006005-0009")/* ��ʾ */,
					hint);
			return;
		}
		String billID = (String) param.getRowData().getData("cbillid");
		String billCode = (String) param.getRowData().getData("vbillcode");
		String cbilltype = (String) param.getRowData().getData("cbilltypecode");

		SourceBillFlowDlg dlg = new SourceBillFlowDlg(WorkbenchEnvironment
				.getInstance().getWorkbench(),
				TOReportLinkQuery.BILL_FINDER_CLASSNAME, cbilltype, billID,
				billCode);

		// ���ü�����
		dlg.setBillGraphListener(new DefaultBillGraphListener());
		dlg.showModal();
	}

}
