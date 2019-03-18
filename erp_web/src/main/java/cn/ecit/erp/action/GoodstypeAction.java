package cn.ecit.erp.action;
import cn.ecit.erp.biz.IGoodstypeBiz;
import cn.ecit.erp.entity.Goodstype;

/**
 * 商品分类Action 
 * @author Administrator
 *
 */
public class GoodstypeAction extends BaseAction<Goodstype> {

	private IGoodstypeBiz goodstypeBiz;
	
	public void setGoodstypeBiz(IGoodstypeBiz goodstypeBiz) {
		this.goodstypeBiz = goodstypeBiz;
		setBaseBiz(goodstypeBiz);
	}
	
	
}
