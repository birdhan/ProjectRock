package cn.it.ssh.service.impl;

import cn.it.ssh.dao.RelatedDao;
import cn.it.ssh.entity.Relate;
import cn.it.ssh.service.ReatedService;

public class RelateServiceImpl implements ReatedService {

	private RelatedDao relate;
	public RelatedDao getRelate() {
		return relate;
	}
	public void setRelate(RelatedDao relate) {
		this.relate = relate;
	}
	@Override
	public void save(Relate rela) {
		this.relate.saveMo(rela);

	}

}
