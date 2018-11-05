package com.codigo.aplios.sdk.core.beans;

import java.util.ArrayList;

import com.codigo.aplios.sdk.core.attribute.Author;
import com.codigo.aplios.sdk.core.attribute.Changelog;
import com.codigo.aplios.sdk.core.attribute.Changelog.Category;

@Changelog(date = "2016.04.15", authors = {
		@Author(name = "", login = "", contact = ""),
		@Author(name = "", login = "", contact = "") }, category = Category.New, descrition = "", title = "")
public class EmailMessage {

	public static void main(final String[] args) throws InterruptedException {

		EmailMessage.from("sd")
				.to("and.radz@wp.pl", "izabela141@wp.pl")
				.subject("Informacja raportowa")
				.content("Test admin")
				.mimeType("html")
				.build();
	}

	private String				from;
	private ArrayList<String>	to;
	private String				subject;
	private String				content;
	private String				mimeType;

	private EmailMessage() {

	}

	public static ITo from(final String from) {

		return new EmailMessage.Builder(
			from);
	}

	public interface ITo {
		IContent to(String to);

		IContent to(String... to);
	}

	public interface ISubject {
		IContent subject(String subject);
	}

	public interface IContent {
		IContent subject(String subject);

		IBuild content(String content);
	}

	public interface IBuild {
		IBuild mimeType(String mimeType);

		EmailMessage build();
	}

	private static class Builder implements ITo, ISubject, IContent, IBuild {
		private final EmailMessage instance = new EmailMessage();

		public Builder(final String from) {

			this.instance.from = from;
		}

		@Override
		public IBuild mimeType(final String mimeType) {

			this.instance.mimeType = mimeType;
			return this;
		}

		@Override
		public EmailMessage build() {

			return this.instance;
		}

		@Override
		public IBuild content(final String content) {

			this.instance.content = content;
			return this;
		}

		@Override
		public IContent subject(final String subject) {

			this.instance.subject = subject;
			return this;
		}

		@Override
		public IContent to(final String to) {

			this.instance.to.add(to);
			return this;
		}

		@Override
		public IContent to(final String... to) {

			for (final String str : to)
				this.instance.to.add(str);
			return this;
		}
	}
}
