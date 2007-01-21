/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.gusuku.helper;

import java.util.List;

import org.seasar.gusuku.dao.CommentDao;
import org.seasar.gusuku.entity.Comment;

/**
 * コメントに関するHelperクラス
 * @author duran
 *
 */
public class CommentHelper {

	private CommentDao commentDao;

	/**
	 * 指定した報告に対するコメント一覧を取得する
	 * @param reportid 対象となる報告ID
	 * @return コメント一覧
	 */
	public List<Comment> getCommentList(String reportid) {
		return commentDao.findByReportid(reportid);
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 指定したIDに従うコメントを取得する
	 * @param cid コメントID
	 * @return コメント
	 */
	public Comment getComment(String cid) {
		return commentDao.findById(cid);
	}

}
